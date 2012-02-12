package org.gerryai.htn.simple.constraint.validation.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.gerryai.htn.domain.Condition;
import org.gerryai.htn.simple.constraint.ValidatableAfterConstraint;
import org.gerryai.htn.simple.constraint.ValidatableBeforeConstraint;
import org.gerryai.htn.simple.constraint.ValidatableBetweenConstraint;
import org.gerryai.htn.simple.constraint.ValidatablePrecedenceConstraint;
import org.gerryai.htn.simple.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.junit.Test;

public class GenericConstraintValidatorImplTest {

	/**
	 * Test that the first constraint is valid.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public void testValidatePrecedenceNoExistingConstraints() throws InvalidConstraint {
	
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraint.getProcedingTask()).thenReturn(mockTaskB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraint));
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidatePrecedenceNoExistingConstraintsMissingTaskA() {
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraint.getProcedingTask()).thenReturn(mockTaskB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidatePrecedenceNoExistingConstraintsMissingTaskB() {
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraint.getProcedingTask()).thenReturn(mockTaskB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public void testValidatePrecedenceOneExistingConstraint() throws InvalidConstraint {
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskC = mock(Task.class);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintB.getProcedingTask()).thenReturn(mockTaskC);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskA);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTask()).thenReturn(mockTaskB);
		when(mockConstraintB.getProcedingTask()).thenReturn(mockTaskA);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskC = mock(Task.class);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTask()).thenReturn(mockTaskB);
		when(mockConstraintB.getProcedingTask()).thenReturn(mockTaskC);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintC = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTask()).thenReturn(mockTaskC);
		when(mockConstraintC.getProcedingTask()).thenReturn(mockTaskA);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskC = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskD = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskE = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskF = mock(Task.class);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintA.getProcedingTask()).thenReturn(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTask()).thenReturn(mockTaskB);
		when(mockConstraintB.getProcedingTask()).thenReturn(mockTaskC);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintC = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTask()).thenReturn(mockTaskC);
		when(mockConstraintC.getProcedingTask()).thenReturn(mockTaskA);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintD = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintD.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintD.getProcedingTask()).thenReturn(mockTaskD);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintE = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintE.getPrecedingTask()).thenReturn(mockTaskA);
		when(mockConstraintE.getProcedingTask()).thenReturn(mockTaskE);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Term, Task<Term>, Condition> mockConstraintF = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintF.getPrecedingTask()).thenReturn(mockTaskB);
		when(mockConstraintF.getProcedingTask()).thenReturn(mockTaskE);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
	
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasks = new HashSet<Task<Term>>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateBeforeNoExistingConstraintsMissingTaskA() {
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasks = new HashSet<Task<Term>>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateBeforeNoExistingConstraintsMissingTaskB() {
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasks = new HashSet<Task<Term>>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public void testValidateBeforeOneExistingConstraint() throws InvalidConstraint {
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasksA = new HashSet<Task<Term>>();
		mockTasksA.add(mockTaskA);
		Set<Task<Term>> mockTasksB = new HashSet<Task<Term>>();
		mockTasksB.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Term, Task<Term>, Condition> mockConstraintB = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		Set<Task<Term>> mockTasksA = new HashSet<Task<Term>>();
		mockTasksA.add(mockTaskA);
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
	
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasks = new HashSet<Task<Term>>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateAfterNoExistingConstraintsMissingTaskA() {
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasks = new HashSet<Task<Term>>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidateAfterNoExistingConstraintsMissingTaskB() {
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasks = new HashSet<Task<Term>>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public void testValidateAfterOneExistingConstraint() throws InvalidConstraint {
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasksA = new HashSet<Task<Term>>();
		mockTasksA.add(mockTaskA);
		Set<Task<Term>> mockTasksB = new HashSet<Task<Term>>();
		mockTasksB.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatableAfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Term, Task<Term>, Condition> mockConstraintB = mock(ValidatableAfterConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		Set<Task<Term>> mockTasksA = new HashSet<Task<Term>>();
		mockTasksA.add(mockTaskA);
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatableAfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
	
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskC = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskD = mock(Task.class);
		Set<Task<Term>> mockTasksA = new HashSet<Task<Term>>();
		mockTasksA.add(mockTaskA);
		mockTasksA.add(mockTaskB);
		Set<Task<Term>> mockTasksB = new HashSet<Task<Term>>();
		mockTasksB.add(mockTaskC);
		mockTasksB.add(mockTaskD);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasksA = new HashSet<Task<Term>>();
		mockTasksA.add(mockTaskA);
		mockTasksA.add(mockTaskB);

		@SuppressWarnings("unchecked")
		Task<Term> mockTaskC = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskD = mock(Task.class);
		Set<Task<Term>> mockTasksB = new HashSet<Task<Term>>();
		mockTasksB.add(mockTaskC);
		mockTasksB.add(mockTaskD);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasksA = new HashSet<Task<Term>>();
		mockTasksA.add(mockTaskA);
		mockTasksA.add(mockTaskB);

		@SuppressWarnings("unchecked")
		Task<Term> mockTaskC = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskD = mock(Task.class);
		Set<Task<Term>> mockTasksB = new HashSet<Task<Term>>();
		mockTasksB.add(mockTaskC);
		mockTasksB.add(mockTaskD);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Term, Task<Term>, Condition> mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskC = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskD = mock(Task.class);
		Set<Task<Term>> mockTasksA = new HashSet<Task<Term>>();
		mockTasksA.add(mockTaskA);
		Set<Task<Term>> mockTasksB = new HashSet<Task<Term>>();
		mockTasksB.add(mockTaskB);
		Set<Task<Term>> mockTasksC = new HashSet<Task<Term>>();
		mockTasksC.add(mockTaskC);
		Set<Task<Term>> mockTasksD = new HashSet<Task<Term>>();
		mockTasksD.add(mockTaskD);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Term, Task<Term>, Condition> mockConstraintB = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksD);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
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
		
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskA = mock(Task.class);
		@SuppressWarnings("unchecked")
		Task<Term> mockTaskB = mock(Task.class);
		Set<Task<Term>> mockTasksA = new HashSet<Task<Term>>();
		mockTasksA.add(mockTaskA);
		Set<Task<Term>> mockTasksB = new HashSet<Task<Term>>();
		mockTasksB.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Term, Task<Term>, Condition> mockConstraintA = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Term, Task<Term>, Condition> validator
				= new GenericConstraintValidator<Term, Task<Term>, Condition>();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertFalse(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		// TODO - should check equals gets called, if possible
	}
	
}
