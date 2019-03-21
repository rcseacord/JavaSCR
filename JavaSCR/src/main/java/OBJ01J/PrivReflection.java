// The MIT License (MIT)
// 
// Copyright (c) 2017 Robert C. Seacord
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package OBJ01J;

import java.lang.reflect.Field;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.util.Enumeration;

class PrivReflection {
  public static void main(String[] args) throws Exception {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      System.out.println("Security manager installed.");

      // Construct a code source with the code base
      CodeSource cs = new PrivReflection().getClass().getProtectionDomain().getCodeSource();
      System.out.println("path: " + cs.getLocation().getPath());

      // Get all granted permissions
      PermissionCollection collectPerm = Policy.getPolicy().getPermissions(cs);
      
      // View each permission in the permission collection
      Enumeration<Permission> pernEnum = collectPerm.elements();

      while (pernEnum.hasMoreElements()) {
        System.out.println(pernEnum.nextElement());
      }
    } else {
      System.out.println("No security manager.");
    }

    // Returns an array of Field objects reflecting
    // all the fields declared by the class (including private)
    final Field[] fields = FieldTest.class.getDeclaredFields();

    // Enumerate fields
    for (Field field : fields) {
      System.out.println("Field: " + field);
    }

    for (Field field : fields) {
      if ("barPrivStr".equals(field.getName())) {
        FieldTest fieldTest = new FieldTest();

        try {
          // Get field
          System.out.println(field.get(fieldTest));
        } catch (IllegalAccessException iae) {
          System.err.println("java.lang.IllegalAccessException" + iae.toString());
        }
        // Make private field accessible
        // Policy file: C:/Users/rseacord/.java.policy
        field.setAccessible(true);
        // Get field
        System.out.println(field.get(fieldTest));

        // Set field
        field.set(fieldTest, "fighters");
        System.out.println(field.get(fieldTest));
        break;
      }
    }
  }
}