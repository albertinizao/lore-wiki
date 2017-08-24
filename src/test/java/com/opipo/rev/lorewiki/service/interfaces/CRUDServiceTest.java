package com.opipo.rev.lorewiki.service.interfaces;

import java.io.Serializable;

public interface CRUDServiceTest<T extends Object, I extends Serializable> extends ExistsTest<T, I>, FindTest<T,I>, DeleteTest<T,I>, CreationTest<T,I> {
}
