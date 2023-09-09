package org.itstep.myClassWork.september09.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.myClassWork.september09.models.interfaces.SerializableToBytes;

import java.io.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  implements Serializable, SerializableToBytes
{
    private String name;

    private UUID user_id; // = UUID.randomUUID();
    private UUID customer_id;

    public byte[] toBytes(){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        byte[] bytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            bytes = bos.toByteArray();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                bos.close();
            } catch (Exception ex) {}
        }
//        System.out.print("Bytes: ");
//        System.out.println(bytes);
        return bytes;
    }

    public static User fromBytes(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        User u = null;

//        System.out.print("Bytes: ");
//        System.out.println(bytes);

        try {
            in = new ObjectInputStream(bis);
            u = (User)in.readObject();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        return u;
    }
}
