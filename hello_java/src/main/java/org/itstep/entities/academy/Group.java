package org.itstep.entities.academy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@AllArgsConstructor
// @NoArgsConstructor // Иначе я не смогу создать сущность

public class Group {

   public Group(String name) {
       this.setName(name);
   }

    private String name;
    private ArrayList<Student> students = new ArrayList<>();

    public String toString() {
        StringBuilder students = new StringBuilder();
        for (Student s:this.getStudents()) {
            students.append(s.getName()).append(", ");
        }
        return "Group( name=" + this.getName() + ", students=[" + students.toString() + "] )";
    }

}
