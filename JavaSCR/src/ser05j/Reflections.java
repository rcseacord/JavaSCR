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

package ser05j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import sun.reflect.ReflectionFactory;

public class Reflections {

  public static Field getField(final Class<?> clazz, final String fieldName) throws Exception {
    Field field = clazz.getDeclaredField(fieldName);
    if (field != null)
      field.setAccessible(true);
    else if (clazz.getSuperclass() != null)
      field = getField(clazz.getSuperclass(), fieldName);
    return field;
  }

  public static void setFieldValue(final Object obj, final String fieldName, final Object value) throws Exception {
    final Field field = getField(obj.getClass(), fieldName);
    field.set(obj, value);
  }

  public static Object getFieldValue(final Object obj, final String fieldName) throws Exception {
    final Field field = getField(obj.getClass(), fieldName);
    return field.get(obj);
  }

  public static Constructor<?> getFirstCtor(final String name) throws Exception {
    final Constructor<?> ctor = Class.forName(name).getDeclaredConstructors()[0];
    ctor.setAccessible(true);
    return ctor;
  }

  public static <T> T createWithoutConstructor(Class<T> classToInstantiate)
      throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    return createWithConstructor(classToInstantiate, Object.class, new Class[0], new Object[0]);
  }

  @SuppressWarnings({ "unchecked" })
  public static <T> T createWithConstructor(Class<T> classToInstantiate, Class<? super T> constructorClass,
      Class<?>[] consArgTypes, Object[] consArgs)
      throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Constructor<? super T> objCons = constructorClass.getDeclaredConstructor(consArgTypes);
    objCons.setAccessible(true);
    Constructor<?> sc = ReflectionFactory.getReflectionFactory().newConstructorForSerialization(classToInstantiate,
        objCons);
    sc.setAccessible(true);
    return (T) sc.newInstance(consArgs);
  }

}