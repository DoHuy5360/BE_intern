package com.example.demo.kit.query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.kit.tray.DatabaseTableNameTray;

@Repository
public interface DatabaseTableNameQuery extends CrudRepository<DatabaseTableNameTray, String> {
    @Query(value = "SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog'  AND schemaname != 'information_schema' AND tablename NOT LIKE '%tray'", nativeQuery = true)
    List<DatabaseTableNameTray> getAllTableName();
}
