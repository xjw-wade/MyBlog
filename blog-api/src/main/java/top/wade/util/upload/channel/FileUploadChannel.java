package top.wade.util.upload.channel;

import top.wade.util.upload.UploadUtils;

/**
 * @Author xjw
 * @Date 2023/7/17 21:15
 * @Description: 文件上传方式
 */
public interface FileUploadChannel {
    /**
     * 通过指定方式上传文件
     *
     * @param image 需要保存的图片
     * @return 访问图片的URL
     * @throws Exception
     */
    String upload(UploadUtils.ImageResource image);

}
