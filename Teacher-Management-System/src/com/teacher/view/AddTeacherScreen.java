package com.teacher.view;

import com.teacher.dao.TeacherDao;
import com.teacher.entities.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeacherScreen extends JFrame implements ActionListener
{
    private JLabel teacherId, teacherName, teacherSalary, teacherAddress;
    private JTextField tfId, tfName, tfSalary, tfAddress;
    private JButton btnSave, btnClear;

    public AddTeacherScreen()
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
        add(tfName);

        teacherAddress = new JLabel("Address : ");
        teacherAddress.setBounds(50, 140, 100, 30);
        teacherAddress.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(teacherAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(160, 140, 250, 30);
        tfAddress.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfAddress);

        teacherSalary = new JLabel("Salary : ");
        teacherSalary.setBounds(50, 200, 100, 30);
        teacherSalary.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(teacherSalary);

        tfSalary = new JTextField();
        tfSalary.setBounds(160, 200, 250, 30);
        tfSalary.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfSalary);


        btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        btnSave.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnSave.setBounds(100, 300, 100, 30);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        add(btnSave);

        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);
        btnClear.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnClear.setBounds(250, 300, 100, 30);
        btnClear.setBackground(Color.BLACK);
        btnClear.setForeground(Color.WHITE);
        add(btnClear);


        getContentPane().setBackground(Color.white);
        setTitle("Add Teacher Screen");
        setSize(500,450);
        setLocation(440, 300);
        setResizable(false);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command =e.getActionCommand();

        if(command.equals("Save"))
        {
            Teacher teacher = new Teacher(Integer.parseInt(tfId.getText()), tfName.getText(), tfAddress.getText(), Double.parseDouble(tfSalary.getText()));

            TeacherDao teacherDao = new TeacherDao();
            if(teacherDao.saveTeacher(teacher))
            {
                JOptionPane.showMessageDialog(null, "Teacher has been saved successfully");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Failed to save Teacher");
            }
            tfId.setText("");
            tfName.setText("");
            tfAddress.setText("");
            tfSalary.setText("");
            tfId.requestFocus();
        }

        if (command.equals("Clear"))
        {
            tfId.setText("");
            tfName.setText("");
            tfAddress.setText("");
            tfSalary.setText("");
            tfId.requestFocus();
        }
    }
}
