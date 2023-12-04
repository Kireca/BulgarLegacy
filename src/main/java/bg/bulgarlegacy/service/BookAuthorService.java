package bg.bulgarlegacy.service;


import bg.bulgarlegacy.model.entites.BookAuthorEntity;

public interface BookAuthorService {


    BookAuthorEntity createAuthor(String firstName , String lastName);
}
