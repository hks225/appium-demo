package lib.elements;

import org.openqa.selenium.By;

public class TextBox extends BaseElement {

    public TextBox(final By locator, final String name) {
        super(locator, name);
    }

    public TextBox(final By locator) {
        super(locator);
    }

    /**
     * Enter the text in the box
     *
     * @param value text
     */
    public void type(final String value) {
        waitAndAssertIsPresent();
        logger.info(String.format("Typing in '" + this.getName() +
                "' with Locator:'" + this.getLocator() + "' Value: " + value));
        getElement().sendKeys(value);
    }

    public boolean isReadOnly() {
        waitAndAssertIsPresent();
        String attr = getElement().getAttribute("readonly");
        return attr.contains("true") | attr.contains("readonly");
    }

    /**
     * Clear field and type text
     *
     * @param value text
     */
    public void setText(final String value) {
        waitAndAssertIsPresent();
        getElement().clear();
        type(value);
    }

    public void submit() {
        waitAndAssertIsPresent();
        getElement().submit();
    }

    public String getValue() {
        waitAndAssertIsPresent();
        return getElement().getAttribute("value");
    }

}
