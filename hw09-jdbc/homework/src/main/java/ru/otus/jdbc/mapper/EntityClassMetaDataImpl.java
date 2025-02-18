package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.crm.model.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private static final Logger log = LoggerFactory.getLogger(EntityClassMetaDataImpl.class);
    private final Class<T> entityClass;

    public EntityClassMetaDataImpl(Class<T> entityClass1) {
        this.entityClass = entityClass1;
        log.debug("Entity class is: {}", entityClass);
    }

    @Override
    public String getName() {
        return entityClass.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return entityClass.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Field getIdField() {
        List<Field> fields = getAllFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return List.of(entityClass.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        List<Field> fields = getAllFields();
        fields.removeIf(field -> field.isAnnotationPresent(Id.class));
        return null;
    }
}
