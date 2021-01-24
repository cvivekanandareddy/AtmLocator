package com.vivekananda.atm.model;

import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import org.junit.jupiter.api.Test;

class CommonPojoTest {

    @Test
    void testPojoStructureAndBehavior() {
        Validator validator = ValidatorBuilder.create()
                .with(new SetterTester())
                .with(new GetterTester())
                .build();

        validator.validate(this.getClass().getPackage().getName(), new FilterPackageInfo());
    }

}
