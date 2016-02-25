package fileminer.view.cellrenderer;

import java.awt.Color;
import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

import fileminer.model.FileSystemTreeImpl;

public class NodeTreeCellRenderer implements TreeCellRenderer {

    private final FileSystemTreeImpl fst;
    private final JLabel label;

    /**
     * @param fst file system object
     */
    public NodeTreeCellRenderer(final FileSystemTreeImpl fst) {
        this.fst = fst;
        this.label = new JLabel(" ");
        label.setOpaque(true);
    }

    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value,
                                                  final boolean selected, final boolean expanded,
                                                  final boolean leaf, final int row, final boolean hasFocus) {

        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        final File file = (File) node.getUserObject();

        if (file != null) {
            label.setIcon(fst.getFileIcon(file));
            label.setText(fst.getFileText(file));
        } else {
            label.setText(value.toString());
        }
         
        if (selected) {
            label.setBackground(Color.BLUE);
            label.setForeground(Color.WHITE);
        } else {
            label.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
        }

        return label;
    }

}
