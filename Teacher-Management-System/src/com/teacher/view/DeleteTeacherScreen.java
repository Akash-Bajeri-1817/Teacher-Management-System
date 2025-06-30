package com.teacher.view;

import com.teacher.dao.TeacherDao;
import com.teacher.entities.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteTeacherScreen extends JFrame implements ActionListener
{
    private JLabel teacherId, teacherName, teacherSalary, teacherAddress;
    private JTextField tfId, tfName, tfSalary, tfAddress;
    private JButton btnSearch, btnDelete;

    public DeleteTeacherScreen()
    {
        setLayout(null);

        teacherId = new JLabel("ID  : ");
        teacherId.setBounds(50, 30, 100, 30);
        teacherId.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(teacherId);

        tfId = new JTextField();
        tfId.setBounds(160, 30, 250, 30);
        tfId.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfId);

        teacherName = new JLabel("Name : ");
        teacherName.setBounds(50, 85, 100, 30);
        teacherName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(teacherName);

        tfName = new JTextField();
        tfName.setBounds(160, 85, 250, 30);
        tfName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tfName.setEditable(false);
        add(tfName);

        teacherAddress = new JLabel("Address : ");
        teacherAddress.setBounds(50, 140, 100, 30);
        teacherAddress.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(teacherAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(160, 140, 250, 30);
        tfAddress.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tfAddress.setEditable(false);
        add(tfAddress);

        teacherSalary = new JLabel("Salary : ");
        teacherSalary.setBounds(50, 200, 100, 30);
        teacherSalary.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(teacherSalary);

        tfSalary = new JTextField();
        tfSalary.setBounds(160, 200, 250, 30);
        tfSalary.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tfSalary.setEditable(false);
        add(tfSalary);


        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);
        btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnSearch.setBounds(100, 300, 100, 30);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        add(btnSearch);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnDelete.setBounds(250, 300, 100, 30);
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        add(btnDelete);


        getContentPane().setBackground(Color.white);
        setTitle("Display Teacher Screen");
        setSize(500,450);
        setLocation(440, 300);
        setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command =e.getActionCommand();

        if(command.equals("Search"))
        {
            TeacherDao td = new TeacherDao();
            Teacher t = td.searchTeacherById(Integer.parseInt(tfId.getText()));

            if(t != null)
            {
                tfName.setText(t.getName());
                tfAddress.setText(t.getAddress());
                tfSalary.setText(Double.toString(t.getSalary()));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Id " + tfId.getText() + " not found");
            }

        }

        if (command.equals("Delete"))
        {
            TeacherDao td = new TeacherDao();
            if(td.deleteTeacherById(Integer.parseInt(tfId.getText())))
            {
                JOptionPane.showMessageDialog(null, "Teacher Id" + tfId.getText()  + " deleted successfully");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Id " + tfId.getText() + " not found");
            }
            tfId.setText("");
            tfName.setText("");
            tfAddress.setText("");
            tfSalary.setText("");
            tfId.requestFocus();
        }
    }
}
