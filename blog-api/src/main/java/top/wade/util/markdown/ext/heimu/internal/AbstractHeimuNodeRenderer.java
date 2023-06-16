package top.wade.util.markdown.ext.heimu.internal;

import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import top.wade.util.markdown.ext.heimu.Heimu;

import java.util.Collections;
import java.util.Set;

/**
 * @Author xjw
 * @Date 2023/6/13 13:14
 * @Description:
 */
abstract class AbstractHeimuNodeRenderer implements NodeRenderer {
    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.<Class<? extends Node>>singleton(Heimu.class);
    }
}
