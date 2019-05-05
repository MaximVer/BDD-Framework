package com.sqac.test.rules;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.core.domain.JavaCall.Predicates.target;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.type;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.name;
import static com.tngtech.archunit.core.domain.properties.HasOwner.Predicates.With.owner;
import static com.tngtech.archunit.core.domain.properties.HasParameterTypes.Predicates.parameterTypes;
import static com.tngtech.archunit.lang.conditions.ArchConditions.callMethodWhere;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
import static com.tngtech.archunit.library.GeneralCodingRules.THROW_GENERIC_EXCEPTIONS;

public class CodingRulesTest {

	private JavaClasses classes;

	private static ArchCondition<JavaClass> callOfThreadSleep() {
		return callMethodWhere(target(name("sleep")).and(target(owner(type(Thread.class))))
				.and(target(parameterTypes("long").or(parameterTypes("long", "int"))))).as("call of Thread.sleep");
	}

	@BeforeEach
	public void setUp() {
		classes = new ClassFileImporter().importPackages("com.sqac.test.ui.tests", "com.sqac.test.ui.pages");
	}

	@Test
	public void classes_should_not_access_standard_streams_defined_by_hand() {
		noClasses().should(ACCESS_STANDARD_STREAMS).check(classes);
	}

	@Test
	public void classes_should_not_throw_generic_exceptions() {
		noClasses().should(THROW_GENERIC_EXCEPTIONS).check(classes);
	}

	@Test
	public void classes_should_not_use_java_util_logging() {
		NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING.check(classes);
	}

	@Test
	public void classes_should_not_use_thread_sleep() {
		noClasses().should(callOfThreadSleep()).check(classes);
	}
}
