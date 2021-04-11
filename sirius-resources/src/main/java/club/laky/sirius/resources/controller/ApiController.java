package club.laky.sirius.resources.controller;

import club.laky.sirius.resources.entity.ImgLog;
import club.laky.sirius.resources.feign.FeignCacheService;
import club.laky.sirius.resources.service.ImgLogService;
import club.laky.sirius.resources.utils.ResultMap;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Resource
    private ImgLogService service;

    @Value("${server.servlet.context-path}")
    private String project;

    @Value("${server.port}")
    private String port;

    @Value("${system.realm}")
    private String realm;

    @Value(value = "${system.imgUpLoadPath}")
    private String imgUpLoadPath;

    @Autowired
    private FeignCacheService cacheService;

    @ResponseBody
    @PostMapping("/upload")
    public Object imgUpload(@RequestParam("token") String token, @RequestParam("upload") MultipartFile file) {
        try {
            logger.info("-------------上传图片-------------");
            if (file.isEmpty()) {
                return ResultMap.error("文件不能为空!");
            }
            Map<String, Object> result = (Map<String, Object>) cacheService.get(token);
            logger.info("获取到token：{}的数据：{}", token, result.toString());
            JSONObject jsonObject = JSONObject.parseObject((String) result.get("data"));
            if (!service.hasUser(jsonObject.getInteger("id"))) {
                return ResultMap.error("不存在该用户,无法上传图片");
            }
            String fileOriginName = file.getOriginalFilename();
            if (fileOriginName == null) {
                return ResultMap.error("文件名异常!");
            }
            String suffix = fileOriginName.substring(fileOriginName.lastIndexOf("."));
            if (suffix.equals(".png") || suffix.equals(".jpg") || suffix.equals(".jpeg")) {
                String fileName = uploadImg(file);
                String newFileName = getShowName(fileName);
                ImgLog fileLog = new ImgLog(newFileName, jsonObject.getInteger("id"), DateUtil.now());
                if (service.insert(fileLog) == null) {
                    return ResultMap.error("上传失败");
                }
                return ResultMap.success(newFileName);
            }
            return ResultMap.error("上传文件类型不对,请上传图片文件!如.jpg、.png、.jpeg为后缀的文件!");
        } catch (Exception e) {
            logger.error("上传图片失败：" + e.getMessage());
            return ResultMap.error(e.getMessage());
        }
    }

    /**
     * 获取图片资源的完整路径
     */
    private String getShowName(String fileName) {
        StringBuilder builder;
        builder = new StringBuilder("http://");
        builder.append(realm)
                .append(":")
                .append(port)
                .append("/")
                .append(project)
                .append("/static/")
                .append(fileName);
        return builder.toString();
    }

    private String uploadImg(MultipartFile file) throws IOException {
        String fileName = getFileName(file.getOriginalFilename());
        String realPath = imgUpLoadPath + "/" + fileName;
        isExist(imgUpLoadPath);
        File dest = new File(realPath);
        file.transferTo(dest);
        return fileName;
    }

    private void isExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName 源文件名
     * @return 新文件名
     */
    private String getFileName(String fileOriginName) {
        if (fileOriginName == null) {
            return null;
        }
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String suffix = fileOriginName.substring(fileOriginName.lastIndexOf("."));
        return fileName + suffix;
    }
}
