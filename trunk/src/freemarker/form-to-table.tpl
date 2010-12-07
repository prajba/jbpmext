<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
	"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
	<class entity-name="${formName}" table="c_${tableName}">
		<id name="id" column="obj_id" type="integer">
			<generator class="native"></generator>
		</id>
		<property name="beginTime" column="begin_time" type="date" not-null="true">
		</property>
		<property name="endTime" column="end_time" type="date">
		</property>
		<property name="usableStatus" column="usable_status" type="integer" not-null="true">
		</property>
		<many-to-one name="draftsman" class="org.jbpmext.model.Member">
			<column name="draftsman_id"></column>
		</many-to-one>
		<many-to-one name="permission" class="org.jbpmext.model.ObjectPermission">
			<column name="permission_id"></column>
		</many-to-one>
<#list fields as col>
		<property name="${col.fieldName}" type="${col.dataType}">
			<column name="${col.columnName}"<#if col.dataType == "string"> length="200"</#if>></column>
		</property>
</#list>
	</class>
</hibernate-mapping>