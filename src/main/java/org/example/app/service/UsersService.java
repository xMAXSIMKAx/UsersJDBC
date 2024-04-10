package org.example.app.service;

import org.example.app.entity.Users;
import org.example.app.entity.UsersMapper;
import org.example.app.exceptions.UsersException;
import org.example.app.repository.impl.UsersRepository;
import org.example.app.utils.Constants;
import org.example.app.utils.UsersValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UsersService {

    UsersRepository repository = new UsersRepository();

    public String create(Map<String, String> data) {
        Map<String, String> errors =
                new UsersValidator().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UsersException("Check inputs", errors);
            } catch (UsersException e) {
                return e.getErrors(errors);
            }
        }
        return repository.create(new UsersMapper().mapData(data));
    }

    public String read() {
        // Отримуємо дані
        Optional<List<Users>> optional = repository.read();
        // Якщо Optional не містить null, формуємо виведення.
        // Інакше повідомлення про відсутність даних.
        if (optional.isPresent()) {
            // Отримуємо колекцію з Optional
            List<Users> list = optional.get();
            // Якщо колекція не порожня, формуємо виведення.
            // Інакше повідомлення про відсутність даних.
            if (!list.isEmpty()) {
                AtomicInteger count = new AtomicInteger(0);
                StringBuilder sb = new StringBuilder();
                list.forEach((contact) ->
                        sb.append(count.incrementAndGet())
                                .append(") ")
                                .append(contact.toString())
                );
                return sb.toString();
            } else return Constants.DATA_ABSENT_MSG;
        } else return Constants.DATA_ABSENT_MSG;
    }

    public String update(Map<String, String> data) {
        Map<String, String> errors =
                new UsersValidator().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UsersException("Check inputs", errors);
            } catch (UsersException e) {
                return e.getErrors(errors);
            }
        }
        return repository.update(new UsersMapper().mapData(data));
    }

    public String delete(Map<String, String> data) {
        Map<String, String> errors =
                new UsersValidator().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UsersException("Check inputs", errors);
            } catch (UsersException e) {
                return e.getErrors(errors);
            }
        }
        return repository.delete(new UsersMapper().mapData(data).getId());
    }

    public String readById(Map<String, String> data) {
        Map<String, String> errors =
                new UsersValidator().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UsersException("Check inputs", errors);
            } catch (UsersException e) {
                return e.getErrors(errors);
            }
        }
        // Отримуємо дані
        Optional<Users> optional =
                repository.readById(Long.parseLong(data.get("id")));
        // Якщо Optional не містить null, формуємо виведення.
        // Інакше повідомлення про відсутність даних.
        if (optional.isPresent()) {
            // Отримуємо об'єкт з Optional
            Users contact = optional.get();
            return contact.toString();
        } else return Constants.DATA_ABSENT_MSG;
    }
}
