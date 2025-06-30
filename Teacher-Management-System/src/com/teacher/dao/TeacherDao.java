package com.teacher.dao;

import com.teacher.config.Configuration;
import com.teacher.entities.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao
{
    public boolean saveTeacher(Teacher teacher)
    {
        FileWriter fw = null;

        try {
            fw =new FileWriter(Configuration.FILE_NAME,true);
            fw.write(teacher.getId() + "," + teacher.getName() + "," + teacher.getSalary() + "," +  teacher.getAddress() + "\n");
            //System.out.println("Teacher save successfully");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                fw.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public  void saveAllTeacher(List<Teacher> t)
    {
        for(Teacher teacher: t)
        {
            saveTeacher(teacher);
        }
    }

   public List<Teacher> getAllTeacher()
    {
        List<Teacher> teachers = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;

        try
        {
            fr = new FileReader(Configuration.FILE_NAME);
            br = new BufferedReader(fr);

            while(true)
            {
                String line = br.readLine();

                if(line==null)break;

                String[] lineArray = line.split(",");
                int id = Integer.parseInt(lineArray[0]);
                String name = lineArray[1];
                double salary = Double.parseDouble(lineArray[2]);
                String address = lineArray[3];

                Teacher ob = new Teacher(id, name, address, salary);
                teachers.add(ob);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            try
            {
                fr.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return teachers;
    }

    public Teacher searchTeacherById(int id)
    {
        List<Teacher> t = getAllTeacher();

        if(t.isEmpty())
        {
            return null;
        }

        for(Teacher teacher: t)
        {
            if(teacher.getId() == id)
            {
                return teacher;
            }
        }
        return null;
    }

  public boolean deleteTeacherById(int id)
    {
        List<Teacher> t = getAllTeacher();

        if(t.isEmpty())
        {
            return false;
        }

        for(Teacher teacher: t)
        {
            if(teacher.getId() == id)
            {
                t.remove(teacher);

                File f = new File(Configuration.FILE_NAME);
                f.delete();

                try
                {
                    f.createNewFile();
                    saveAllTeacher(t);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public boolean updateTeacherById(int id, String name,  double salary, String address)
    {
        List<Teacher> t = getAllTeacher();

        if(t.isEmpty())
        {
            return false;
        }

        for(Teacher teacher: t)
        {
            if(teacher.getId() == id)
            {
                teacher.setName(name);
                teacher.setSalary(salary);
                teacher.setAddress(address);

                File f = new File(Configuration.FILE_NAME);
                f.delete();

                try
                {
                    f.createNewFile();
                    saveAllTeacher(t);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        return false;
    }


}
