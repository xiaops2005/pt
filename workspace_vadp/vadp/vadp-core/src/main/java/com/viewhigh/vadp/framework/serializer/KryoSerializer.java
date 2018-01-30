package com.viewhigh.vadp.framework.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;

import sun.reflect.ReflectionFactory;

/**
 * 
 * 序列化集合时会自动序列化里面类型，将来可以先序列化Class对象，
 * 然后再序列化Object,将两个byte合为一个保存，反序列化时在分别读出
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年5月31日
 * 修改日期: 2017年5月31日
 */
@Service("serializer")
public class KryoSerializer implements ObjectSerializable {

	/**
	 * Kryo反序列化需要无参构造函数，通过ReflectionFactory可以创建
	 * 
	 * @author hecq
	 * 
	 */
	class Kryox extends Kryo {
		private final ReflectionFactory REFLECTION_FACTORY = ReflectionFactory.getReflectionFactory();

		private final ConcurrentHashMap<Class<?>, Constructor<?>> _constructors = new ConcurrentHashMap<Class<?>, Constructor<?>>();

		public <T> T newInstance(Class<T> type) {
			try {
				return super.newInstance(type);
			} catch (Exception e) {
				return (T) newInstanceFromReflectionFactory(type);
			}
		}

		private Object newInstanceFrom(Constructor<?> constructor) {
			try {
				return constructor.newInstance();
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}

		private <T> T newInstanceFromReflectionFactory(Class type) {
			Constructor<?> constructor = _constructors.get(type);
			if (constructor == null) {
				constructor = newConstructorForSerialization(type);
				Constructor<?> saved = _constructors.putIfAbsent(type, constructor);
				if (saved != null)
					constructor = saved;
			}
			return (T) newInstanceFrom(constructor);
		}

		private <T> Constructor<?> newConstructorForSerialization(Class<T> type) {
			try {
				Constructor<?> constructor = REFLECTION_FACTORY.newConstructorForSerialization(type, Object.class.getDeclaredConstructor());
				constructor.setAccessible(true);
				return constructor;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	KryoFactory factory = new KryoFactory() {
		public Kryo create() {
			Kryox kryo = new Kryox();
			// configure kryo instance, customize settings
			kryo.register(HashMap.class);
			return kryo;
		}
	};

	KryoPool pool = new KryoPool.Builder(factory).softReferences().build();

	public Object serial(Object object) {
		Kryo kryo = pool.borrow();
		List list = new ArrayList();
		list.add(object);
		Class<? extends Object> clazz = object.getClass();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Output output = new Output(byteArrayOutputStream);
		kryo.writeObject(output, list);
		output.close();
		pool.release(kryo);

		return byteArrayOutputStream.toByteArray();
	}

	public Object unserial(Object value) {
		Kryo kryo = pool.borrow();
		Input input = new Input(new ByteArrayInputStream((byte[]) value));
		Object readObject = kryo.readObject(input, ArrayList.class);
		input.close();
		pool.release(kryo);
		return ((ArrayList) readObject).get(0);
	}
}
