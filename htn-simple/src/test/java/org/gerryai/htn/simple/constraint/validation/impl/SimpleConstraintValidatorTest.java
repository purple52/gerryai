package org.gerryai.htn.simple.constraint.validation.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gerryai.htn.constraint.AfterConstraint;
import org.gerryai.htn.constraint.BeforeConstraint;
import org.gerryai.htn.constraint.BetweenConstraint;
import org.gerryai.htn.constraint.PrecedenceConstraint;
import org.gerryai.htn.tasknetwork.InvalidConstraint;
import org.gerryai.htn.tasknetwork.Task;
import org.gerryai.logic.Term;
import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Unit tests for SimpleConstraintValidator class.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleConstraintValidatorTest {

    /**
     * Test adding single tasks.
     */
    @Test
    public final void testTasks() {

        Task mockTaskA = mock(Task.class);
        Task mockTaskB = mock(Task.class);
        
        SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
        
        // Check that the task set contains only the tasks we have just added
        assertEquals(2, validator.getTasks().size());
        assertTrue(validator.getTasks().contains(mockTaskA));
        assertTrue(validator.getTasks().contains(mockTaskB));
    }
    
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
		PrecedenceConstraint mockConstraint = mock(PrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockPrecedingTasks);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockProcedingTasks);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		validator.add(mockConstraint);
		
		assertEquals(2, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertEquals(1, validator.getPrecedenceConstraints().size());
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraint));
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 * @throws InvalidConstraint thrown if test passes
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidatePrecedenceNoExistingConstraintsMissingTaskA() throws InvalidConstraint {
		
        Task mockTaskA = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockTaskB);
		PrecedenceConstraint mockConstraint = mock(PrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockPrecedingTasks);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockProcedingTasks);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskB);
		
		assertEquals(1, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskB));
		
		validator.add(mockConstraint);
	}

	/**
	 * Test that the first constraint is invalid if a task is missing.
	 * @throws InvalidConstraint 
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidatePrecedenceNoExistingConstraintsMissingTaskB() throws InvalidConstraint {
		
        Task mockTaskA = mock(Task.class);
        Set<Task> mockPrecedingTasks = new HashSet<Task>();
        mockPrecedingTasks.add(mockTaskA);
        Task mockTaskB = mock(Task.class);
        Set<Task> mockProcedingTasks = new HashSet<Task>();
        mockProcedingTasks.add(mockTaskB);
		
		PrecedenceConstraint mockConstraint = mock(PrecedenceConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockPrecedingTasks);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockProcedingTasks);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);

		assertEquals(1, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		
		validator.add(mockConstraint);
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint only if test fails
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
		
		PrecedenceConstraint mockConstraintA = mock(PrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		PrecedenceConstraint mockConstraintB = mock(PrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksC);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockConstraintA);
		
		validator.add(mockTaskC);
		validator.add(mockConstraintB);
		
		final int expectedNumTasks = 3;
		assertEquals(expectedNumTasks, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertTrue(validator.getTasks().contains(mockTaskC));
		
		assertEquals(2, validator.getPrecedenceConstraints().size());
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintA));
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintB));
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
		
		PrecedenceConstraint mockConstraintA = mock(PrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockConstraintA);
		
		assertEquals(2, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		
		validator.add(mockConstraintA);
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
        
		PrecedenceConstraint mockConstraintA = mock(PrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksA);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		
		assertEquals(1, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		
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
		
		PrecedenceConstraint mockConstraintA = mock(PrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		PrecedenceConstraint mockConstraintB = mock(PrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksA);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		validator.add(mockConstraintA);
		
		assertEquals(2, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertEquals(1, validator.getPrecedenceConstraints().size());
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintA));
		
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
		
		PrecedenceConstraint mockConstraintA = mock(PrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		PrecedenceConstraint mockConstraintB = mock(PrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksC);
		
		PrecedenceConstraint mockConstraintC = mock(PrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintC.getProcedingTasks()).thenReturn(mockTasksA);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		
		validator.add(mockConstraintA);
		validator.add(mockConstraintB);
		
		final int expectedNumTasks = 3;
		assertEquals(expectedNumTasks, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertTrue(validator.getTasks().contains(mockTaskC));
		assertEquals(2, validator.getPrecedenceConstraints().size());
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintA));
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintB));
		
		validator.add(mockConstraintC);
	}

	/**
	 * Test that adding a cyclic set of constraints fails, cycle of three with extra constraints.
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
		
		PrecedenceConstraint mockConstraintA = mock(PrecedenceConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		PrecedenceConstraint mockConstraintB = mock(PrecedenceConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksC);
		
		PrecedenceConstraint mockConstraintC = mock(PrecedenceConstraint.class);
		when(mockConstraintC.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintC.getProcedingTasks()).thenReturn(mockTasksA);
		
		PrecedenceConstraint mockConstraintD = mock(PrecedenceConstraint.class);
		when(mockConstraintD.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintD.getProcedingTasks()).thenReturn(mockTasksD);
		
		PrecedenceConstraint mockConstraintE = mock(PrecedenceConstraint.class);
		when(mockConstraintE.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintE.getProcedingTasks()).thenReturn(mockTasksE);
		
		PrecedenceConstraint mockConstraintF = mock(PrecedenceConstraint.class);
		when(mockConstraintF.getPrecedingTasks()).thenReturn(mockTasksB);
		when(mockConstraintF.getProcedingTasks()).thenReturn(mockTasksE);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
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
		
		final int expectedNumTasks = 6;
		final int expectedNumConstraints = 5;
		assertEquals(expectedNumTasks, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertTrue(validator.getTasks().contains(mockTaskC));
		assertTrue(validator.getTasks().contains(mockTaskD));
		assertTrue(validator.getTasks().contains(mockTaskE));
		assertEquals(expectedNumConstraints, validator.getPrecedenceConstraints().size());
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintA));
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintC));
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintD));
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintE));
		assertTrue(validator.getPrecedenceConstraints().contains(mockConstraintF));
		
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
		
		BeforeConstraint mockConstraint = mock(BeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		validator.add(mockConstraint);
		
		assertEquals(2, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertTrue(validator.getBeforeConstraints().contains(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 * @throws InvalidConstraint thrown if test passes
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidateBeforeNoExistingConstraintsMissingTaskA() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		BeforeConstraint mockConstraint = mock(BeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskB);
		
		assertEquals(1, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskB));
		
		validator.add(mockConstraint);
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 * @throws InvalidConstraint thrown if test passes
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidateBeforeNoExistingConstraintsMissingTaskB() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		BeforeConstraint mockConstraint = mock(BeforeConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		
		assertEquals(1, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		
		validator.add(mockConstraint);
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint only if the test fails
	 */
	@Test
	public final void testValidateBeforeOneExistingConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		
		BeforeConstraint mockConstraintA = mock(BeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		BeforeConstraint mockConstraintB = mock(BeforeConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockConstraintA);
		validator.add(mockConstraintB);
		
		assertEquals(2, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertEquals(2, validator.getBeforeConstraints().size());
		assertTrue(validator.getBeforeConstraints().contains(mockConstraintA));
		assertTrue(validator.getBeforeConstraints().contains(mockConstraintB));
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
		
		BeforeConstraint mockConstraintA = mock(BeforeConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockConstraintA);

		assertEquals(1, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertEquals(1, validator.getBeforeConstraints().size());
		assertTrue(validator.getBeforeConstraints().contains(mockConstraintA));
		
		validator.add(mockConstraintA);
		
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
		
		AfterConstraint mockConstraint = mock(AfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidator validator
				= new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		
		validator.add(mockConstraint);
		
		assertEquals(2, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertEquals(1, validator.getAfterConstraints().size());
		assertTrue(validator.getAfterConstraints().contains(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 * @throws InvalidConstraint thrown if test passes
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidateAfterNoExistingConstraintsMissingTaskA() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		AfterConstraint mockConstraint = mock(AfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskB);
		
		assertEquals(1, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskB));
		
		validator.add(mockConstraint);
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 * @throws InvalidConstraint thrown if test passes
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidateAfterNoExistingConstraintsMissingTaskB() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasks = new HashSet<Task>();
		mockTasks.add(mockTaskA);
		mockTasks.add(mockTaskB);
		
		AfterConstraint mockConstraint = mock(AfterConstraint.class);
		when(mockConstraint.getTasks()).thenReturn(mockTasks);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		
		assertEquals(1, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		
		validator.add(mockConstraint);
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public final void testValidateAfterOneExistingConstraint() throws InvalidConstraint {
		
		Task mockTaskA = mock(Task.class);
		Task mockTaskB = mock(Task.class);
		Set<Task> mockTasksA = new HashSet<Task>();
		mockTasksA.add(mockTaskA);
		Set<Task> mockTasksB = new HashSet<Task>();
		mockTasksB.add(mockTaskB);
		
		AfterConstraint mockConstraintA = mock(AfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		AfterConstraint mockConstraintB = mock(AfterConstraint.class);
		when(mockConstraintB.getTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockConstraintA);
		
		validator.add(mockConstraintB);
		
		assertEquals(2, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertEquals(2, validator.getAfterConstraints().size());
		assertTrue(validator.getAfterConstraints().contains(mockConstraintA));
		assertTrue(validator.getAfterConstraints().contains(mockConstraintB));
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
		
		AfterConstraint mockConstraintA = mock(AfterConstraint.class);
		when(mockConstraintA.getTasks()).thenReturn(mockTasksA);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockConstraintA);
		
		assertEquals(1, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertEquals(1, validator.getAfterConstraints().size());
		assertTrue(validator.getAfterConstraints().contains(mockConstraintA));
		
		validator.add(mockConstraintA);
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
		
		BetweenConstraint mockConstraint = mock(BetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		validator.add(mockTaskD);
		
		validator.add(mockConstraint);
		
		final int expectedNumTasks = 4;
		assertEquals(expectedNumTasks, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertTrue(validator.getTasks().contains(mockTaskC));
		assertTrue(validator.getTasks().contains(mockTaskD));
		assertEquals(1, validator.getBetweenConstraints().size());
		assertTrue(validator.getBetweenConstraints().contains(mockConstraint));
	}
	
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 * @throws InvalidConstraint thrown if test passes
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidateBetweenNoExistingConstraintsMissingTaskA() throws InvalidConstraint {
		
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
		
		BetweenConstraint mockConstraint = mock(BetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		validator.add(mockTaskD);
		
		final int expectedNumTasks = 3;
		assertEquals(expectedNumTasks, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertTrue(validator.getTasks().contains(mockTaskC));
		assertTrue(validator.getTasks().contains(mockTaskD));
		
		validator.add(mockConstraint);
	}
	
	/**
	 * Test that the first constraint is invalid if a task is missing.
	 * @throws InvalidConstraint thrown if test passes
	 */
	@Test(expected = InvalidConstraint.class)
	public final void testValidateAfterNoExistingConstraintsMissingTaskD() throws InvalidConstraint {
		
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
		
		BetweenConstraint mockConstraint = mock(BetweenConstraint.class);
		when(mockConstraint.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraint.getProcedingTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		
		final int expectedNumTasks = 3;
		assertEquals(expectedNumTasks, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertTrue(validator.getTasks().contains(mockTaskC));
		
		validator.add(mockConstraint);
	}
	
	/**
	 * Test that a second valid constraint can be validated.
	 * @throws InvalidConstraint only if test fails
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
		
		BetweenConstraint mockConstraintA = mock(BetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		BetweenConstraint mockConstraintB = mock(BetweenConstraint.class);
		when(mockConstraintB.getPrecedingTasks()).thenReturn(mockTasksC);
		when(mockConstraintB.getProcedingTasks()).thenReturn(mockTasksD);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockTaskC);
		validator.add(mockTaskD);
		
		validator.add(mockConstraintA);
		validator.add(mockConstraintB);
		
		final int expectedNumTasks = 4;
		assertEquals(expectedNumTasks, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertTrue(validator.getTasks().contains(mockTaskC));
		assertTrue(validator.getTasks().contains(mockTaskD));
		assertEquals(2, validator.getBetweenConstraints().size());
		assertTrue(validator.getBetweenConstraints().contains(mockConstraintA));
		assertTrue(validator.getBetweenConstraints().contains(mockConstraintB));
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
		BetweenConstraint mockConstraintA = mock(BetweenConstraint.class);
		when(mockConstraintA.getPrecedingTasks()).thenReturn(mockTasksA);
		when(mockConstraintA.getProcedingTasks()).thenReturn(mockTasksB);
		
		SimpleConstraintValidator validator = new SimpleConstraintValidator();
		validator.add(mockTaskA);
		validator.add(mockTaskB);
		validator.add(mockConstraintA);
		
		assertEquals(2, validator.getTasks().size());
		assertTrue(validator.getTasks().contains(mockTaskA));
		assertTrue(validator.getTasks().contains(mockTaskB));
		assertEquals(1, validator.getBetweenConstraints().size());
		assertTrue(validator.getBetweenConstraints().contains(mockConstraintA));
		
		validator.add(mockConstraintA);
	}
	
	/**
	 * Test that a substitution is applied to all members.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public final void testApply() throws InvalidConstraint {
		
        @SuppressWarnings("unchecked")
        Map<Term, Term> mockSubstitution = mock(Map.class);
        
		Task mockTaskA = mock(Task.class);
        Task mockTaskB = mock(Task.class);
        Task mockTaskC = mock(Task.class);
        Task mockTaskD = mock(Task.class);
        Set<Task> mockUpdatedTasks = new HashSet<Task>();
        mockUpdatedTasks.add(mockTaskC);
        mockUpdatedTasks.add(mockTaskD);
        Multimap<Task, Task> mockTaskMap = HashMultimap.create();
        mockTaskMap.put(mockTaskA, mockTaskC);
        mockTaskMap.put(mockTaskB, mockTaskD);
        
        when(mockTaskA.applyToCopy(mockSubstitution)).thenReturn(mockTaskC);
        when(mockTaskB.applyToCopy(mockSubstitution)).thenReturn(mockTaskD);
        
        AfterConstraint mockAfterConstraintA = mock(AfterConstraint.class);
        AfterConstraint mockAfterConstraintB = mock(AfterConstraint.class);
        AfterConstraint mockAfterConstraintC = mock(AfterConstraint.class);
        AfterConstraint mockAfterConstraintD = mock(AfterConstraint.class);
        AfterConstraint mockAfterConstraintE = mock(AfterConstraint.class);
        AfterConstraint mockAfterConstraintF = mock(AfterConstraint.class);
        Set<AfterConstraint> mockUpdatedAfterConstraints = new HashSet<AfterConstraint>();
        mockUpdatedAfterConstraints.add(mockAfterConstraintE);
        mockUpdatedAfterConstraints.add(mockAfterConstraintF);
        
        when(mockAfterConstraintA.apply(mockSubstitution)).thenReturn(mockAfterConstraintC);
        when(mockAfterConstraintB.apply(mockSubstitution)).thenReturn(mockAfterConstraintD);
        when(mockAfterConstraintC.replace(mockTaskMap)).thenReturn(mockAfterConstraintE);
        when(mockAfterConstraintD.replace(mockTaskMap)).thenReturn(mockAfterConstraintF);
        
        BeforeConstraint mockBeforeConstraintA = mock(BeforeConstraint.class);
        BeforeConstraint mockBeforeConstraintB = mock(BeforeConstraint.class);
        BeforeConstraint mockBeforeConstraintC = mock(BeforeConstraint.class);
        BeforeConstraint mockBeforeConstraintD = mock(BeforeConstraint.class);
        BeforeConstraint mockBeforeConstraintE = mock(BeforeConstraint.class);
        BeforeConstraint mockBeforeConstraintF = mock(BeforeConstraint.class);
        Set<BeforeConstraint> mockUpdatedBeforeConstraints = new HashSet<BeforeConstraint>();
        mockUpdatedBeforeConstraints.add(mockBeforeConstraintE);
        mockUpdatedBeforeConstraints.add(mockBeforeConstraintF);
        
        when(mockBeforeConstraintA.apply(mockSubstitution)).thenReturn(mockBeforeConstraintC);
        when(mockBeforeConstraintB.apply(mockSubstitution)).thenReturn(mockBeforeConstraintD);
        when(mockBeforeConstraintC.replace(mockTaskMap)).thenReturn(mockBeforeConstraintE);
        when(mockBeforeConstraintD.replace(mockTaskMap)).thenReturn(mockBeforeConstraintF);

        BetweenConstraint mockBetweenConstraintA = mock(BetweenConstraint.class);
        BetweenConstraint mockBetweenConstraintB = mock(BetweenConstraint.class);
        BetweenConstraint mockBetweenConstraintC = mock(BetweenConstraint.class);
        BetweenConstraint mockBetweenConstraintD = mock(BetweenConstraint.class);
        BetweenConstraint mockBetweenConstraintE = mock(BetweenConstraint.class);
        BetweenConstraint mockBetweenConstraintF = mock(BetweenConstraint.class);
        Set<BetweenConstraint> mockUpdatedBetweenConstraints = new HashSet<BetweenConstraint>();
        mockUpdatedBetweenConstraints.add(mockBetweenConstraintE);
        mockUpdatedBetweenConstraints.add(mockBetweenConstraintF);
        
        when(mockBetweenConstraintA.apply(mockSubstitution)).thenReturn(mockBetweenConstraintC);
        when(mockBetweenConstraintB.apply(mockSubstitution)).thenReturn(mockBetweenConstraintD);
        when(mockBetweenConstraintC.replace(mockTaskMap)).thenReturn(mockBetweenConstraintE);
        when(mockBetweenConstraintD.replace(mockTaskMap)).thenReturn(mockBetweenConstraintF);

        PrecedenceConstraint mockPrecedenceConstraintA = mock(PrecedenceConstraint.class);
        PrecedenceConstraint mockPrecedenceConstraintB = mock(PrecedenceConstraint.class);
        PrecedenceConstraint mockPrecedenceConstraintC = mock(PrecedenceConstraint.class);
        PrecedenceConstraint mockPrecedenceConstraintD = mock(PrecedenceConstraint.class);
        PrecedenceConstraint mockPrecedenceConstraintE = mock(PrecedenceConstraint.class);
        PrecedenceConstraint mockPrecedenceConstraintF = mock(PrecedenceConstraint.class);
        Set<PrecedenceConstraint> mockUpdatedPrecedenceConstraints = new HashSet<PrecedenceConstraint>();
        mockUpdatedPrecedenceConstraints.add(mockPrecedenceConstraintE);
        mockUpdatedPrecedenceConstraints.add(mockPrecedenceConstraintF);
        
        when(mockPrecedenceConstraintA.apply(mockSubstitution)).thenReturn(mockPrecedenceConstraintC);
        when(mockPrecedenceConstraintB.apply(mockSubstitution)).thenReturn(mockPrecedenceConstraintD);
        when(mockPrecedenceConstraintC.replace(mockTaskMap)).thenReturn(mockPrecedenceConstraintE);
        when(mockPrecedenceConstraintD.replace(mockTaskMap)).thenReturn(mockPrecedenceConstraintF);
        
        SimpleConstraintValidator validator = new SimpleConstraintValidator();
        validator.add(mockTaskA);
        validator.add(mockTaskB);
        validator.add(mockAfterConstraintA);
        validator.add(mockAfterConstraintB);
        validator.add(mockBeforeConstraintA);
        validator.add(mockBeforeConstraintB);
        validator.add(mockBetweenConstraintA);
        validator.add(mockBetweenConstraintB);
        validator.add(mockPrecedenceConstraintA);
        validator.add(mockPrecedenceConstraintB);
        
        validator.apply(mockSubstitution);
        
        assertEquals(mockUpdatedTasks, validator.getTasks());
        assertEquals(mockUpdatedAfterConstraints, validator.getAfterConstraints());
        assertEquals(mockUpdatedBeforeConstraints, validator.getBeforeConstraints());
        assertEquals(mockUpdatedBetweenConstraints, validator.getBetweenConstraints());
        assertEquals(mockUpdatedPrecedenceConstraints, validator.getPrecedenceConstraints());
	}
	
	/**
	 * Test replace.
	 * @throws InvalidConstraint only if test fails
	 */
	@Test
	public final void testReplace() throws InvalidConstraint {
		fail("Not yet implemented");
	}
}
