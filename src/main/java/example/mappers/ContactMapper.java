package example.mappers;


import example.beans.Contact;

import java.util.List;

public interface ContactMapper {

	Integer insert(Contact contact);

	List<Contact> selectAll();

	Contact select(Integer id);

	Integer update(Contact contact);

	Integer delete(Integer id);

}
