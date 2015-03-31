package u6.u6a4;

import java.util.ArrayList;

/**
 * Created by Ferdinand on 31.03.2015.
 */
public class Filter implements IFilter {
    @Override
    public ArrayList filterRaw(ArrayList groups) {
        ArrayList<Student> students = new ArrayList<>();
        for(ArrayList<Student> group : ((ArrayList<ArrayList<Student>>)groups)) {
            for(Student student : group) {
                if(student.getPoints() >= criteria*maxNumberofPoints/100) {
                    students.add(student);
                }
            }
        }

        return students;
    }

    @Override
    public ArrayList<Student> filterGeneric(ArrayList<ArrayList<Student>> groups) {
        ArrayList<Student> students = new ArrayList<>();
        for(ArrayList<Student> group : groups) {
            for(Student student : group) {
                if(student.getPoints() >= criteria*maxNumberofPoints/100) {
                    students.add(student);
                }
            }
        }

        return students;
    }
}
