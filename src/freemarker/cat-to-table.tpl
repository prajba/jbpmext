<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
	"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
	<class entity-name="${displayName}" table="dict_${tableName}">
		<id name="id" column="entry_id" type="integer">
			<generator class="native"></generator>
		</id>
		<property name="usableStatus" column="usable_status" type="integer" not-null="true">
		</property>
		<property name="key" type="string">
			<column name="dict_key" length="200"></column>
		</property>
		<property name="value" type="${valueType}">
			<column name="dict_value"<#if valueType == "string"> length="200"</#if>></column>
		</property>
	</class>
</hibernate-mapping>