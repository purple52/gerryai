package org.gerryai.htn.simple.constraint.validation.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.gerryai.htn.simple.constraint.ValidatablePrecedenceConstraint;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.junit.Test;

public class SimpleConstraintValidatorImplTest {

	/**
	 * Test that the first constraint is valid.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public void testValidateNoExistingConstraints() throws InvalidConstraint {
	
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);

		ValidatablePrecedenceConstraint<?> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraint.getProcedingTask()).thenReturn(mockTaskB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		validator.add(mockConstraint);
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateNoExistingConstraintsMissingTaskA() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);

		ValidatablePrecedenceConstraint<?> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraint.getProcedingTask()).thenReturn(mockTaskB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateNoExistingConstraintsMissingTaskB() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);

		ValidatablePrecedenceConstraint<?> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraint.getProcedingTask()).thenReturn(mockTaskB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public void testValidateOneExistingConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Task mockTaskC = mock(Task.class);
		
		ValidatablePrecedenceConstraint<?> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		ValidatablePrecedenceConstraint<?> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintB.getProcedingTask()).thenReturn(mockTaskC);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockConstraintA);
		
		validator.add(mockTaskC);
		validator.add(mockConstraintB);
	}
	
	/**
	 * Test that adding the same constraint twice fails.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidateOneExistingIdenticalConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		
		ValidatablePrecedenceConstraint<?> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		ValidatablePrecedenceConstraint<?> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintB.getProcedingTask()).thenReturn(mockTaskB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		validator.add(mockConstraintA);
		validator.add(mockConstraintB);
	}

	/**
	 * Test that adding a cyclic set of constraints fails, cycle of one.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidateSingleCycle() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		
		ValidatablePrecedenceConstraint<?> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskA);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		
		validator.add(mockConstraintA);
	}
	
	/**
	 * Test that adding a cyclic set of constraints fails, cycle of two.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidateDoubleCycle() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		
		ValidatablePrecedenceConstraint<?> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		ValidatablePrecedenceConstraint<?> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTask()).thenReturn(mockTaskB);
		when(mockConstraintB.getProcedingTask()).thenReturn(mockTaskA);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		validator.add(mockConstraintA);
		validator.add(mockConstraintB);
	}
	
	/**
	 * Test that adding a cyclic set of constraints fails, cycle of three.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidateTripleCycle() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Task mockTaskC = mock(Task.class);
		
		ValidatablePrecedenceConstraint<?> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		ValidatablePrecedenceConstraint<?> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTask()).thenReturn(mockTaskB);
		when(mockConstraintB.getProcedingTask()).thenReturn(mockTaskC);
		ValidatablePrecedenceConstraint<?> mockConstraintC = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTask()).thenReturn(mockTaskC);
		when(mockConstraintC.getProcedingTask()).thenReturn(mockTaskA);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		
		validator.add(mockConstraintA);
		validator.add(mockConstraintB);
		validator.add(mockConstraintC);
	}

	/**
	 * Test that adding a cyclic set of constraints fails, cycle of three with extra .
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidateTripleCyclePlusExtras() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Task mockTaskC = mock(Task.class);
		Task mockTaskD = mock(Task.class);
		Task mockTaskE = mock(Task.class);
		Task mockTaskF = mock(Task.class);
		
		ValidatablePrecedenceConstraint<?> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		ValidatablePrecedenceConstraint<?> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTask()).thenReturn(mockTaskB);
		when(mockConstraintB.getProcedingTask()).thenReturn(mockTaskC);
		ValidatablePrecedenceConstraint<?> mockConstraintC = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTask()).thenReturn(mockTaskC);
		when(mockConstraintC.getProcedingTask()).thenReturn(mockTaskA);
		ValidatablePrecedenceConstraint<?> mockConstraintD = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintD.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintD.getProcedingTask()).thenReturn(mockTaskD);
		ValidatablePrecedenceConstraint<?> mockConstraintE = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintE.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintE.getProcedingTask()).thenReturn(mockTaskE);
		ValidatablePrecedenceConstraint<?> mockConstraintF = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintF.getPrecedingTask()).thenReturn(mockTaskB);
		when(mockConstraintF.getProcedingTask()).thenReturn(mockTaskE);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		validator.add(mockTaskD);
		validator.add(mockTaskE);
		validator.add(mockTaskF);

		validator.add(mockConstraintC);
		validator.add(mockConstraintA);
		validator.add(mockConstraintD);
		validator.add(mockConstraintE);
		validator.add(mockConstraintF);
		validator.add(mockConstraintB);

	}
	
}
