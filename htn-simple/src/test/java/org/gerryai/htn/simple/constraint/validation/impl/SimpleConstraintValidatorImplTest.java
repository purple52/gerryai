package org.gerryai.htn.simple.constraint.validation.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.simple.constraint.ValidatableAfterConstraint;
import org.gerryai.htn.simple.constraint.ValidatableBeforeConstraint;
import org.gerryai.htn.simple.constraint.ValidatableBetweenConstraint;
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
	public void testValidatePrecedenceNoExistingConstraints() throws InvalidConstraint {
	
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);

		ValidatablePrecedenceConstraint<?> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraint.getProcedingTask()).thenReturn(mockTaskB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraint));
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidatePrecedenceNoExistingConstraintsMissingTaskA() {
		
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
	public void testValidatePrecedenceNoExistingConstraintsMissingTaskB() {
		
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
	public void testValidatePrecedenceOneExistingConstraint() throws InvalidConstraint {
		
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
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		validator.add(mockTaskC);
		assertTrue(validator.validate(mockConstraintB));
		validator.add(mockConstraintB);
	}
	
	/**
	 * Test that adding the same constraint twice fails.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidatePrecedenceOneExistingIdenticalConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		
		ValidatablePrecedenceConstraint<?> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertFalse(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		// TODO - should check equals gets called, if possible
	}

	/**
	 * Test that adding a cyclic set of constraints fails, cycle of one.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidatePrecedenceSingleCycle() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		
		ValidatablePrecedenceConstraint<?> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskA);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
	}
	
	/**
	 * Test that adding a cyclic set of constraints fails, cycle of two.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidatePrecedenceDoubleCycle() throws InvalidConstraint {
		
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
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertFalse(validator.validate(mockConstraintB));
		validator.add(mockConstraintB);
	}
	
	/**
	 * Test that adding a cyclic set of constraints fails, cycle of three.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidatePrecedenceTripleCycle() throws InvalidConstraint {
		
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
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertTrue(validator.validate(mockConstraintB));
		validator.add(mockConstraintB);
		assertFalse(validator.validate(mockConstraintC));
		validator.add(mockConstraintC);
	}

	/**
	 * Test that adding a cyclic set of constraints fails, cycle of three with extra .
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidatePrecedenceTripleCyclePlusExtras() throws InvalidConstraint {
		
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

		assertTrue(validator.validate(mockConstraintC));
		validator.add(mockConstraintC);
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertTrue(validator.validate(mockConstraintD));
		validator.add(mockConstraintD);
		assertTrue(validator.validate(mockConstraintE));
		validator.add(mockConstraintE);
		assertTrue(validator.validate(mockConstraintF));
		validator.add(mockConstraintF);
		assertFalse(validator.validate(mockConstraintB));
		validator.add(mockConstraintB);

	}

	/**
	 * Test that the first constraint is valid.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public void testValidateBeforeNoExistingConstraints() throws InvalidConstraint {
	
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);

		ValidatableBeforeConstraint<?> mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateBeforeNoExistingConstraintsMissingTaskA() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);

		ValidatableBeforeConstraint<?> mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateBeforeNoExistingConstraintsMissingTaskB() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);

		ValidatableBeforeConstraint<?> mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public void testValidateBeforeOneExistingConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		
		ValidatableBeforeConstraint<?> mockConstraintA = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		ValidatableBeforeConstraint<?> mockConstraintB = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		assertTrue(validator.validate(mockConstraintB));
		validator.add(mockConstraintB);
	}
	
	/**
	 * Test that adding the same constraint twice fails.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidateBeforeOneExistingIdenticalConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		
		ValidatableBeforeConstraint<?> mockConstraintA = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertFalse(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		// TODO - should check equals gets called, if possible
	}
	
	/**
	 * Test that the first constraint is valid.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public void testValidateAfterNoExistingConstraints() throws InvalidConstraint {
	
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);

		ValidatableAfterConstraint<?> mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateAfterNoExistingConstraintsMissingTaskA() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);

		ValidatableAfterConstraint<?> mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateAfterNoExistingConstraintsMissingTaskB() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);

		ValidatableAfterConstraint<?> mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public void testValidateAfterOneExistingConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		
		ValidatableAfterConstraint<?> mockConstraintA = mock(ValidatableAfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		ValidatableAfterConstraint<?> mockConstraintB = mock(ValidatableAfterConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		assertTrue(validator.validate(mockConstraintB));
		validator.add(mockConstraintB);
	}

	/**
	 * Test that adding the same constraint twice fails.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidateAfterOneExistingIdenticalConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		
		ValidatableAfterConstraint<?> mockConstraintA = mock(ValidatableAfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertFalse(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		// TODO - should check equals gets called, if possible
	}
	
	/**
	 * Test that the first constraint is valid.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public void testValidateBetweenNoExistingConstraints() throws InvalidConstraint {
	
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		mockTasksA.add(mockTaskB);

		Task mockTaskC = mock(Task.class);
		Task mockTaskD = mock(Task.class);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskC);
		mockTasksB.add(mockTaskD);
		
		ValidatableBetweenConstraint<?> mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		validator.add(mockTaskD);
		
		assertTrue(validator.validate(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateBetweenNoExistingConstraintsMissingTaskA() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		mockTasksA.add(mockTaskB);

		Task mockTaskC = mock(Task.class);
		Task mockTaskD = mock(Task.class);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskC);
		mockTasksB.add(mockTaskD);
		
		ValidatableBetweenConstraint<?> mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		validator.add(mockTaskD);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateAfterNoExistingConstraintsMissingTaskD() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		mockTasksA.add(mockTaskB);

		Task mockTaskC = mock(Task.class);
		Task mockTaskD = mock(Task.class);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskC);
		mockTasksB.add(mockTaskD);
		
		ValidatableBetweenConstraint<?> mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public void testValidateBetweenOneExistingConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		Task mockTaskC = mock(Task.class);
		Set<Task> mockTasksC = new HashSet<Task>();
		mockTasksC.add(mockTaskC);
		Task mockTaskD = mock(Task.class);
		Set<Task> mockTasksD = new HashSet<Task>();
		mockTasksD.add(mockTaskD);
		
		ValidatableBetweenConstraint<?> mockConstraintA = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		ValidatableBetweenConstraint<?> mockConstraintB = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksD);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		validator.add(mockTaskD);
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);

		assertTrue(validator.validate(mockConstraintB));
		validator.add(mockConstraintB);
	}
	
	/**
	 * Test that adding the same constraint twice fails.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected=InvalidConstraint.class)
	public void testValidateBetweenOneExistingIdenticalConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		
		ValidatableBetweenConstraint<?> mockConstraintA = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidatorImpl validator = new SimpleConstraintValidatorImpl();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertFalse(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		// TODO - should check equals gets called, if possible
	}
	
}
