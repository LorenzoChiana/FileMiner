package fileminer.cellrenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

import fileminer.model.Node;

/**
 * This class implements a TreeCellRenderer to specify how the node should be rendered in the viewport.
 * @author Michele
 *
 */
public class NodeTreeCellRenderer implements TreeCellRenderer {

    private final JLabel label;

    /**
     * Constructor of NodeTreeCellRenderer.
     */
    public NodeTreeCellRenderer() {
        this.label = new JLabel("");
        label.setOpaque(true);
    }

    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value,
                                                  final boolean selected, final boolean expanded,
                                                  final boolean leaf, final int row, final boolean hasFocus) {

        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        final Node fileNode = (Node) node.getUserObject();

        if (fileNode != null) {
            label.setIcon(fileNode.getFileIcon());
            label.setText(fileNode.getFileName());
        } else {
            label.setText(value.toString());
        }
         
        if (selected && hasFocus) {
            label.setBackground(SystemColor.textHighlight);
            label.setForeground(Color.WHITE);
        } else if (selected) {
            label.setBackground(Color.LIGHT_GRAY);
            label.setForeground(Color.WHITE);
        } else {
            label.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
        }

        return label;
    }

}
