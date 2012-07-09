package org.gerryai.htn.simple.constraint.validation.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

/**
 * Unit tests for GenericConstraintValidatorImpl class.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class GenericConstraintValidatorImplTest {

	/**
	 * Test that the first constraint is valid.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public final void testValidatePrecedenceNoExistingConstraints() throws InvalidConstraint {
	
		Task mockTaskA = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockTaskA);
		Task mockTaskB = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockTaskB);
		ValidatablePrecedenceConstraint mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockPrecedingTasks);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockProcedingTasks);
		
		GenericConstraintValidator validator = new GenericConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraint));
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public final void testValidatePrecedenceNoExistingConstraintsMissingTaskA() {
		
        Task mockTaskA = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockTaskB);
		ValidatablePrecedenceConstraint mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockPrecedingTasks);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockProcedingTasks);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public final void testValidatePrecedenceNoExistingConstraintsMissingTaskB() {
		
        Task mockTaskA = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockTaskB);
		
		ValidatablePrecedenceConstraint mockConstraint = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockPrecedingTasks);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockProcedingTasks);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public final void testValidatePrecedenceOneExistingConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
		Task mockTaskB = mock(Task.class);
        Set<Task> mockTasksB = new HashSet<Task>();
        mockTasksB.add(mockTaskB);
        Task mockTaskC = mock(Task.class);
        Set<Task> mockTasksC = new HashSet<Task>();
        mockTasksC.add(mockTaskC);
		
		ValidatablePrecedenceConstraint mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		ValidatablePrecedenceConstraint mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksC);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	 * @throws InvalidConstraint if test passes
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidatePrecedenceOneExistingIdenticalConstraint() throws InvalidConstraint {
		
        Task mockTaskA = mock(Task.class);
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockTasksB = new HashSet<Task>();
        mockTasksB.add(mockTaskB);
		
		ValidatablePrecedenceConstraint mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	 * @throws InvalidConstraint if test passes
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidatePrecedenceSingleCycle() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
        
		ValidatablePrecedenceConstraint mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
	}
	
	/**
	 * Test that adding a cyclic set of constraints fails, cycle of two.
	 * @throws InvalidConstraint if test succeeds
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidatePrecedenceDoubleCycle() throws InvalidConstraint {
		
        Task mockTaskA = mock(Task.class);
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockTasksB = new HashSet<Task>();
        mockTasksB.add(mockTaskB);
		
		ValidatablePrecedenceConstraint mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		ValidatablePrecedenceConstraint mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	@Test(expected = InvalidConstraint.class)
	public final void testValidatePrecedenceTripleCycle() throws InvalidConstraint {
		
        Task mockTaskA = mock(Task.class);
        Set<Task> mockTasksA = new HashSet<Task>();
        mockTasksA.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockTasksB = new HashSet<Task>();
        mockTasksB.add(mockTaskB);
        Task mockTaskC = mock(Task.class);
        Set<Task> mockTasksC = new HashSet<Task>();
        mockTasksC.add(mockTaskC);
		
		ValidatablePrecedenceConstraint mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		ValidatablePrecedenceConstraint mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksC);
		
		ValidatablePrecedenceConstraint mockConstraintC = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintC.getProcedingTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	@Test(expected = InvalidConstraint.class)
	public final void testValidatePrecedenceTripleCyclePlusExtras() throws InvalidConstraint {
		
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
		
		ValidatablePrecedenceConstraint mockConstraintA = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		ValidatablePrecedenceConstraint mockConstraintB = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksC);
		
		ValidatablePrecedenceConstraint mockConstraintC = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintC.getProcedingTasks()).thenReturn(mockTasksA);
		
		ValidatablePrecedenceConstraint mockConstraintD = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintD.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintD.getProcedingTasks()).thenReturn(mockTasksD);
		
		ValidatablePrecedenceConstraint mockConstraintE = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintE.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintE.getProcedingTasks()).thenReturn(mockTasksE);
		
		ValidatablePrecedenceConstraint mockConstraintF = mock(ValidatablePrecedenceConstraint.class);
		when(mockConstraintF.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintF.getProcedingTasks()).thenReturn(mockTasksE);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	public final void testValidateBeforeNoExistingConstraints() throws InvalidConstraint {
	
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		ValidatableBeforeConstraint mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public final void testValidateBeforeNoExistingConstraintsMissingTaskA() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		ValidatableBeforeConstraint mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public final void testValidateBeforeNoExistingConstraintsMissingTaskB() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		ValidatableBeforeConstraint mockConstraint = mock(ValidatableBeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public final void testValidateBeforeOneExistingConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		
		ValidatableBeforeConstraint mockConstraintA = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		ValidatableBeforeConstraint mockConstraintB = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	@Test(expected = InvalidConstraint.class)
	public final void testValidateBeforeOneExistingIdenticalConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		
		ValidatableBeforeConstraint mockConstraintA = mock(ValidatableBeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	public final void testValidateAfterNoExistingConstraints() throws InvalidConstraint {
	
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		ValidatableAfterConstraint mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public final void testValidateAfterNoExistingConstraintsMissingTaskA() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		ValidatableAfterConstraint mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskB);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public final void testValidateAfterNoExistingConstraintsMissingTaskB() {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		ValidatableAfterConstraint mockConstraint = mock(ValidatableAfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskA);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint 
	 */
	@Test
	public final void testValidateAfterOneExistingConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		
		ValidatableAfterConstraint mockConstraintA = mock(ValidatableAfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		ValidatableAfterConstraint mockConstraintB = mock(ValidatableAfterConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	@Test(expected = InvalidConstraint.class)
	public final void testValidateAfterOneExistingIdenticalConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		
		ValidatableAfterConstraint mockConstraintA = mock(ValidatableAfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	public final void testValidateBetweenNoExistingConstraints() throws InvalidConstraint {
	
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
		
		ValidatableBetweenConstraint mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	public final void testValidateBetweenNoExistingConstraintsMissingTaskA() {
		
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
		
		ValidatableBetweenConstraint mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		validator.add(mockTaskD);
		
		assertFalse(validator.validate(mockConstraint));
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 */
	@Test
	public final void testValidateAfterNoExistingConstraintsMissingTaskD() {
		
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
		
		ValidatableBetweenConstraint mockConstraint = mock(ValidatableBetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	public final void testValidateBetweenOneExistingConstraint() throws InvalidConstraint {
		
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
		
		ValidatableBetweenConstraint mockConstraintA = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		ValidatableBetweenConstraint mockConstraintB = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksD);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
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
	@Test(expected = InvalidConstraint.class)
	public final void testValidateBetweenOneExistingIdenticalConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		ValidatableBetweenConstraint mockConstraintA = mock(ValidatableBetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		GenericConstraintValidator validator
				= new GenericConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		assertTrue(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		assertFalse(validator.validate(mockConstraintA));
		validator.add(mockConstraintA);
		
		// TODO - should check equals gets called, if possible
	}
	
}
