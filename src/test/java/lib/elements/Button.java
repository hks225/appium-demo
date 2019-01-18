package lib.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement {

    /**
     * @param locator
     * @param name
     */
    public Button(final By locator, final String name) {
        super(locator, name);
    }

    public Button(By locator) {
        super(locator);
    }

    public boolean isEnabled() {
        return this.getElement().isEnabled();
    }
}
