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
import org.junit.Test;

public class GenericConstraintValidatorImplTest {

	/**
	 * Test that the first constraint is valid.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public void testValidatePrecedenceNoExistingConstraints() throws InvalidConstraint {
	
		Task mockTaskA = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockTaskA);
		Task mockTaskB = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockPrecedingTasks);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockProcedingTasks);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockPrecedingTasks);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockProcedingTasks);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public void testValidatePrecedenceNoExistingConstraintsMissingTaskB() {
		
        Task mockTaskA = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockPrecedingTasks);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockProcedingTasks);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
		Task mockTaskB = mock(Task.class);
        Set<Task> mockTasksB = new HashSet<Task>();
        mockTasksB.add(mockTaskB);
        Task mockTaskC = mock(Task.class);
        Set<Task> mockTasksC = new HashSet<Task>();
        mockTasksC.add(mockTaskC);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksC);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockTasksB = new HashSet<Task>();
        mockTasksB.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
        @SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockTasksB = new HashSet<Task>();
        mockTasksB.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockTasksB = new HashSet<Task>();
        mockTasksB.add(mockTaskB);
        Task mockTaskC = mock(Task.class);
        Set<Task> mockTasksC = new HashSet<Task>();
        mockTasksC.add(mockTaskC);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksC);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintC = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintC.getProcedingTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
        Task mockTaskE = mock(Task.class);
        Set<Task> mockTasksE = new HashSet<Task>();
        mockTasksE.add(mockTaskE);
        Task mockTaskF = mock(Task.class);
        Set<Task> mockTasksF = new HashSet<Task>();
        mockTasksF.add(mockTaskF);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksC);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintC = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintC.getProcedingTasks()).thenReturn(mockTasksA);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintD = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintD.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintD.getProcedingTasks()).thenReturn(mockTasksD);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintE = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintE.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintE.getProcedingTasks()).thenReturn(mockTasksE);
		@SuppressWarnings("unchecked")
		ValidatablePrecedenceConstraint<Task, Condition> mockConstraintF = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintF.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintF.getProcedingTasks()).thenReturn(mockTasksE);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Task, Condition> mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Task, Condition> mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Task, Condition> mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Task, Condition> mockConstraintA = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Task, Condition> mockConstraintB = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableBeforeConstraint<Task, Condition> mockConstraintA = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Task, Condition> mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Task, Condition> mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Task, Condition> mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Task, Condition> mockConstraintA = mock(ValidatableAfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Task, Condition> mockConstraintB = mock(ValidatableAfterConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableAfterConstraint<Task, Condition> mockConstraintA = mock(ValidatableAfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		Task mockTaskC = mock(Task.class);
		Task mockTaskD = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		mockTasksA.add(mockTaskB);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskC);
		mockTasksB.add(mockTaskD);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Task, Condition> mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Task, Condition> mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Task, Condition> mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		Task mockTaskB = mock(Task.class);
		Task mockTaskC = mock(Task.class);
		Task mockTaskD = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		Set<Task> mockTasksC = new HashSet<Task>();
		mockTasksC.add(mockTaskC);
		Set<Task> mockTasksD = new HashSet<Task>();
		mockTasksD.add(mockTaskD);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Task, Condition> mockConstraintA = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Task, Condition> mockConstraintB = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksD);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
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
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		@SuppressWarnings("unchecked")
		ValidatableBetweenConstraint<Task, Condition> mockConstraintA = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator<Task, Condition> validator
				= new GenericConstraintValidator<Task, Condition>();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertFalse(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		// TODO - should check equals gets called, if possible
	}
	
}
