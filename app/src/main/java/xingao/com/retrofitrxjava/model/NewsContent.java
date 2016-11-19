package xingao.com.retrofitrxjava.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class NewsContent implements Serializable {


    /**
     * image_source : Angel Abril Ruiz / CC BY
     * title : 卖衣服的新手段：把耐用品变成「不停买新的」
     * image : http://p4.zhimg.com/30/59/30594279d368534c6c2f91b2c00c7806.jpg
     * share_url : http://daily.zhihu.com/story/3892357
     * js : []
     * ga_prefix : 050615
     * images : []
     * type : 0
     * id : 3892357
     * css : []
     */

    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<?> js;
    private List<?> images;
    private List<?> css;

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<?> getImages() {
        return images;
    }

    public void setImages(List<?> images) {
        this.images = images;
    }

    public List<?> getCss() {
        return css;
    }

    public void setCss(List<?> css) {
        this.css = css;
    }

    @Override
    public String toString() {
        return "NewsContent{" +
                "image_source='" + image_source + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", share_url='" + share_url + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", js=" + js +
                ", images=" + images +
                ", css=" + css +
                '}';
    }
}
