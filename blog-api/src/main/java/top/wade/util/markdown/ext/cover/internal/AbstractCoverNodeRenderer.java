package top.wade.util.markdown.ext.cover.internal;

import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import top.wade.util.markdown.ext.cover.Cover;

import java.util.Collections;
import java.util.Set;

/**
 * @Author xjw
 * @Date 2023/6/13 12:45
 * @Description:
 */
abstract class AbstractCoverNodeRenderer implements NodeRenderer {
    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.<Class<? extends Node>>singleton(Cover.class);
    }
}
