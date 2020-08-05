package xinxin.entity;

public class UIElement {
    /**
     * 元素关键字
     * */
    private String keyword;
    /**
     * 选择器类型
     * */
    private String by;
    /**
     * 选择器的值
     * */
    private String value;

    public UIElement(String keyword, String by, String value) {
        this.keyword = keyword;
        this.by = by;
        this.value = value;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
