package cn.edu.nju.raledon.model;

/**
 * Created by rale on 5/18/17.
 * 图片信息
 */
public class Image {
    /**图片的id**/
    private Long imageId;

    /**图片所属的用户ID**/
    private Long userId;

    /**完整图片名**/
    private String name;

    /**图片所在的域名**/
    private String prefix;

    /**图片所在的文件夹目录**/
    private String directory;

    /**图片的后缀**/
    private String suffix;

    public Image(Long userId, String name, String prefix, String directory, String suffix) {
        this.userId = userId;
        this.name = name;
        this.prefix = prefix;
        this.directory = directory;
        this.suffix = suffix;
    }

    public Image() {
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCompleteURL(){
        return this.getPrefix() + this.getName();
    }

    public String getCompletePath(){
        return this.getDirectory() + this.getName();
    }
}
