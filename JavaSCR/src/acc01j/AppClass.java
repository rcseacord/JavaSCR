// The MIT License (MIT)
// 
// Copyright (c) 2018 Robert C. Seacord
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

package acc01j;

import src.acclib.acclib.LibClass;

import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

class AppClass {
  public static void main(String[] args) {
    // acc01j/java.policy
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      System.out.println("Security manager installed.");
      List<Class<?>> privilegedClasses = new ArrayList<>();
      privilegedClasses.add(AppClass.class);
      privilegedClasses.add(LibClass.class);
      // Display privileges for all code bases
      privilegedClasses.forEach(privilegedClass -> {
        CodeSource cs = AppClass.class.getProtectionDomain().getCodeSource();
        System.out.println("path: " + cs.getLocation().getPath());

        // Get all granted permissions
        PermissionCollection collectPerm = Policy.getPolicy().getPermissions(cs);

        // View each permission in the permission collection
        Enumeration<Permission> permEnum = collectPerm.elements();

        while (permEnum.hasMoreElements()) {
          System.out.println(permEnum.nextElement());
        }
      });
    } else {
      System.out.println("No security manager.");
    }
    System.setProperty(LibClass.OPTIONS, "extra-secure");
    System.out.println(LibClass.getOptions());
  }
}