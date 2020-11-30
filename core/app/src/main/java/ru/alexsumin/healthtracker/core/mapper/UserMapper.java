package ru.alexsumin.healthtracker.core.mapper;

import org.mapstruct.Mapper;
import ru.alexsumin.healthtracker.core.config.IgnoreUnmappedMapperConfig;
import ru.alexsumin.healthtracker.core.domain.entity.User;
import ru.alexsumin.healthtracker.core.api.UserDTO;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface UserMapper {
    User toUser(UserDTO userDTO);
}
