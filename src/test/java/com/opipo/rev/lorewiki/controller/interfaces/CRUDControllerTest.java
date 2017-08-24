package com.opipo.rev.lorewiki.controller.interfaces;

import java.io.Serializable;

public interface CRUDControllerTest<T,I extends Serializable> extends  CreateTest<T,I>, DeleteTest<T,I>, GetTest<T,I>, IdCheckerTest<T,I>, ListTest<T,I>, SaveTest<T,I> {
}
