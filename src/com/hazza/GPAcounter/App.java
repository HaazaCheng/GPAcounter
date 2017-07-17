package com.hazza.GPAcounter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * Created by hazza on 7/17/17.
 */
public class App extends JPanel{
    private JPanel plMain, plFirstLine;
    private JLabel lbFilepath, lbWrter;
    private JTextArea txtFilePath, txtShow;
    private JButton btnSubmit, btnSearch;
    private JFileChooser filechooser;

    public App() {
        init();
    }

    private void init() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gbs = new GridBagConstraints();
        this.setFont(new Font("SansSerif", Font.PLAIN, 14));
        this.setLayout(gridBagLayout);
        gbs.fill = GridBagConstraints.BOTH;

        plMain = new JPanel();
        plFirstLine = new JPanel();
        lbFilepath = new JLabel("文件路径（必须为．xls文件）");
        lbWrter = new JLabel("Written By 程锋(HazzaCheng) hazzacheng@gmail.com");
        txtFilePath = new JTextArea(1, 10);
        btnSubmit = new JButton("计算");
        btnSearch = new JButton("查找");
        txtShow = new JTextArea(20, 35);

        gbs.fill = GridBagConstraints.BOTH;
        gbs.weightx = 1.0;
        gridBagLayout.setConstraints(lbFilepath, gbs);
        gridBagLayout.setConstraints(txtFilePath, gbs);
        gbs.gridwidth = GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(btnSearch, gbs);
        gbs.weightx = 0.0;
        gridBagLayout.setConstraints(btnSubmit, gbs);
        gbs.gridwidth = GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(txtShow, gbs);
        gbs.gridwidth = GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(lbWrter, gbs);

        this.add(lbFilepath);
        this.add(txtFilePath);
        this.add(btnSearch);
        this.add(btnSubmit);
        this.add(txtShow);
        this.add(lbWrter);

        btnSearch.addActionListener(actionEvent ->
                filechooser = getFileChooser());
        btnSubmit.addActionListener(actionEvent ->
                txtShow.setText(DataGetter.getAllData(txtFilePath.getText().trim(), 2)));

        setSize(300, 700);
    }

    private JFileChooser getFileChooser() {
        JFileChooser fc = new JFileChooser(new File("."));
        fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileHidingEnabled(true);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(new MyFilter("xls"));
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            txtFilePath.setText(fc.getSelectedFile().getAbsolutePath());
        }
        return fc;
    }

    class MyFilter extends FileFilter {
        private String ext;

        public MyFilter(String extString) {
            this.ext = extString;
        }

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) return true;
            String extension = getExtension(f);
            if (extension.toLowerCase().equals(this.ext.toLowerCase())) return true;
            else return false;
        }

        public String getDescription() {
            return this.ext.toUpperCase();
        }

        private String getExtension(File f) {
            String name = f.getName();
            int index = name.lastIndexOf('.');
            if (index == -1) return "";
            else return name.substring(index + 1).toLowerCase();
        }
    }

    public static void main(String[] args) {
        App app = new App();
        JFrame f = new JFrame("苏大选课网GPA计算器 V1.0");
        f.setLocation(300, 300);

        f.add("Center", app);
        f.pack();
        f.setSize(f.getPreferredSize());
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
