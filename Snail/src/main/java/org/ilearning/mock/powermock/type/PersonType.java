
package org.ilearning.mock.powermock.type;

/**
 * 功能描述
 *
 * @author yuwenbo
 * @since 2022-08-15
 */
public enum PersonType {
    S("student"),
    N("normal");

    private String type;

    PersonType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
