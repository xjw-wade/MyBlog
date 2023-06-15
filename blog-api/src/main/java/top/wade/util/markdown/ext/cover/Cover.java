package top.wade.util.markdown.ext.cover;

import org.commonmark.node.CustomNode;
import org.commonmark.node.Delimited;

/**
 * @Author xjw
 * @Date 2023/6/13 12:43
 * @Description: A cover node containing text and other inline nodes nodes as children.
 */
public class Cover extends CustomNode implements Delimited {
    private static final String DELIMITER = "%%";

    @Override
    public String getOpeningDelimiter() {
        return DELIMITER;
    }

    @Override
    public String getClosingDelimiter() {
        return DELIMITER;
    }
}
