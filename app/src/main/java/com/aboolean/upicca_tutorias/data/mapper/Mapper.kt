package com.aboolean.upicca_tutorias.data.mapper

/**
 * Map types [T] into type [R]
 * Main purpose of the interface is to encapsulate transformation/mapping logic
 * Convenient for unit testing
 */
interface Mapper<T,R> {
    /**
     * Performs mapping from type [T] into type [R]
     * @return result of transformation
     */
     fun map(item: T): R
}