/**
 *  Gerry AI - Open framework for automated planning algorithms
 *  Copyright (C) 2012  David Edwards
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gerryai.htn.simple.domain.impl;

import org.gerryai.htn.simple.domain.ConditionBuilder;
import org.gerryai.htn.simple.domain.ImmutableDomainBuilder;
import org.gerryai.htn.simple.domain.ImmutableDomainBuilderFactory;
import org.gerryai.htn.simple.domain.EffectBuilder;
import org.gerryai.htn.simple.domain.ImmutableMethodBuilder;
import org.gerryai.htn.simple.domain.OperatorBuilder;

/**
 * Simple implementation of a factory for generating immutable domain builders.
 * @author David Edwards <david@more.fool.me.uk>
 */
public class SimpleDomainBuilderFactory implements ImmutableDomainBuilderFactory {

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableDomainBuilder createDomainBuilder() {
		return new SimpleDomain.Builder();	
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final OperatorBuilder createOperatorBuilder() {
		return new SimpleOperator.Builder();	
	}

	/**
	 * {@inheritDoc}
	 */
	public final ImmutableMethodBuilder createMethodBuilder() {
		return new SimpleMethod.Builder();
	}

	/**
     * {@inheritDoc}
     */
    public final EffectBuilder createEffectBuilder() {
        return new SimpleEffect.Builder();
    }
    
    /**
     * {@inheritDoc}
     */
    public final ConditionBuilder createConditionBuilder() {
        return new SimpleCondition.Builder();
    }
}
