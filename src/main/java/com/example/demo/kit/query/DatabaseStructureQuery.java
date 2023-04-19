package com.example.demo.kit.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.kit.tray.DatabaseStructureTray;

public interface DatabaseStructureQuery extends CrudRepository<DatabaseStructureTray, String> {
    @Query(value = "SELECT column_name as name, data_type as type, character_maximum_length as length, is_nullable as nullable FROM information_schema.columns WHERE table_name = ?1", nativeQuery = true)
    List<DatabaseStructureTray> getDataStructure(String tableName);
}
