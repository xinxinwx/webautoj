package xinxin.entity;

import java.util.List;

public class Page {
    private String keyword;
    private List<UIElement> uiElements;

    public Page(String keyword, List<UIElement> uiElements) {
        this.keyword = keyword;
        this.uiElements = uiElements;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<UIElement> getUiElements() {
        return uiElements;
    }

    public void setUiElements(List<UIElement> uiElements) {
        this.uiElements = uiElements;
    }


}
