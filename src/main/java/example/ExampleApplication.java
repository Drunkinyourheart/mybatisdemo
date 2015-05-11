package example;

import example.beans.Contact;
import example.mappers.ContactMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ExampleApplication {
	/**
	 * scope: application
	 */
	private static SqlSessionFactory sqlMapper = null;

	public static void main(String[] args) {
//		String resource = "org/mybatis/example/configuration.xml";
		String resource = "configuration.xml";
		Reader reader = null;

		try {
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession session = sqlMapper.openSession();
		try {
			ContactMapper mapper = session.getMapper(ContactMapper.class);
			List<Contact> contacts = mapper.selectAll();
			for (Contact contact : contacts) {
                System.out.println("con : " + contact);
            }

            Contact c = new Contact();
            c.setId(100);
            c.setFirstName("a");
            c.setLastName("aa");
            c.setEmail("aa");
            c.setPhone("aaa");
            mapper.insert(c);

            System.out.println("j-------------------");
            List<Contact> contactList = mapper.selectAll();
            for (Contact contact : contactList) {
                System.out.println(contact);
            }


            System.out.println("=======================================");
            Contact cc = new Contact();
            cc.setId(100);
            cc.setFirstName("a");
            cc.setLastName("bb");
            mapper.update(cc);
            System.out.println("===========================");
            List<Contact> contactLists = mapper.selectAll();
            for (Contact contact : contactLists) {
                System.out.println(contact);
            }
        } finally {
			session.close();
		}
	}
}
