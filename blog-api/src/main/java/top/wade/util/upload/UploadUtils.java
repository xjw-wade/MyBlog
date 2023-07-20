package top.wade.util.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import top.wade.constant.UploadConstants;
import top.wade.exception.BadRequestException;
import top.wade.util.upload.channel.FileUploadChannel;

/**
 * @Author xjw
 * @Date 2023/7/17 14:55
 * @Description: 图片下载保存工具类
 */
@Component
@DependsOn("springContextUtils")
public class UploadUtils {
    private static RestTemplate restTemplate;

    private static FileUploadChannel uploadChannel;

    @AllArgsConstructor
    @Getter
    public static class ImageResource {
        byte[] data;
        //图片扩展名 jpg png
        String type;
    }

    /**
     * 通过指定方式存储图片
     *
     * @param image 需要保存的图片
     * @throws Exception
     */
    public static String upload(ImageResource image) {
        return uploadChannel.upload(image);
    }



    /**
     * 从网络获取图片数据
     *
     * @param url 图片URL
     * @return
     */
    public static ImageResource getImageByRequest(String url) {
        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);
        if (UploadConstants.IMAGE.equals(responseEntity.getHeaders().getContentType().getType())) {
            return new ImageResource(responseEntity.getBody(), responseEntity.getHeaders().getContentType().getSubtype());
        }
        throw new BadRequestException("response contentType unlike image");

    }



}
