
/*

package err10j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class Rollback {
	public static void SerializeObjectGraph(FileOutputStream fs, Object rootObj)
    {
        // Save the current position of the file.
        long beforeSerialization = fs.Position;
        try {
            FileOutputStream fileOut =
            new FileOutputStream("../../employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(OMaM);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in ../../employee.ser");
         }
        catch(IOException i) {
            i.printStackTrace();
         }
        try
        {
            // Attempt to serialize the object graph to the file.
            formatter.Serialize(fs, rootObj);
        }
        catch ()
        {  
           // If ANYTHING goes wrong, reset the file back to a good state.
            fs.Position = beforeSerialization;

            // Truncate the file.
            fs.SetLength(fs.Position);

            // NOTE: The preceding code isn't in a finally block because
            // the stream should be reset only when serialization fails.

            // Let the caller(s) know what happened by
            // rethrowing the SAME exception.
            throw;
        }
    } // end public static void SerializeObjectGraph

	static void Main()  {
    	Book OMaM = new Book("Of Mice and Men");

        
        using (FileInputStream fs = new FileInputStream(@"..\..\DataFile.dat", FileMode.Create))
        {
            SerializeObjectGraph(fs, new BinaryFormatter(), new Book("Of Mice and Men"));
        }

        // Keep the console window open in debug mode.
        Console.WriteLine("Press any key to exit.");
        Console.ReadKey();
    }
}
*/