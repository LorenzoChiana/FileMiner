package fileminer.view;

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

    public NodeTreeCellRenderer(final FileSystemTreeImpl fst) {
        this.fst = fst;
        this.label = new JLabel(" ");
        label.setOpaque(true);
    }

    @Override
    public Component getTreeCellRendererComponent(final JTree arg0, final Object arg1,
                                                  final boolean arg2, final boolean arg3,
                                                  final boolean arg4, final int arg5, final boolean arg6) {

        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) arg1;
        final File file = (File) node.getUserObject();
        if (file != null) {
            label.setIcon(fst.getFileIcon(file));
            label.setText(fst.getFileText(file));
        } else {
            label.setText(arg1.toString());
        }
         
        if (arg2) {
            label.setBackground(Color.BLUE);
            label.setForeground(Color.WHITE);
        } else {
            label.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
        }

        return label;
    }

}
