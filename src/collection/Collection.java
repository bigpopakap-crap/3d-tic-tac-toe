
package collection;

import java.util.Iterator;

interface GenericCollection<C> {

    /*
     * CONSTRUCTORS:
     *  - Collection()
     *  - Collection(C...)
     *  - Collection(Collection<? extends C>...)
     */

    public void setFinal();
    public boolean isFinal();
    public void setSizeLimit(int maxSize);
    public int getSizeLimit();
    
    public boolean equals(Collection<C> otherCollection, boolean equalsMethod);
    @Override public String toString();
    //public Collection<C> clone();
    public C[] toArray();

    public Collection<C> createIndependence() throws CloneNotSupportedException;

    public boolean isEmpty();
    public boolean isContains(C toFind, boolean equalsMethod);
    public boolean isContainsString(String toString);
    public boolean isOrderedSubsetOf(Collection<C> otherCollection, boolean equalsMethod) throws CollectionException;
    public boolean isSubsetOf(Collection<C> otherCollection, boolean equalsMethod);
    public boolean isIndependentOf(Collection<C> otherCollection);

    //public Class<? extends Object> getObjectClass();
    public int getNumObjects();

    public C getObjectAt(int index) throws CollectionException;
    public int getNumInstances(C toFind, boolean equalsMethod);
    public int getIndex(C toFind, boolean equalsMethod);
    public int getIndexFrom(C toFind, int startIndex, boolean equalsMethod) throws CollectionException;
    public int getLastIndex(C toFind, boolean equalsMethod);
    public int[] getIndeces(C toFind, boolean equalsMethod);

    public C getObjectString(String toString);
    public C getObjectStringFrom(String toString, int startIndex) throws CollectionException;
    public int getNumInstancesString(String toString);
    public int getIndexString(String toString);
    public int getIndexStringFrom(String toString, int startIndex) throws CollectionException;
    public int getLastIndexString(String toString);
    public int[] getIndecesString(String toString);

    public Collection<C> addObjects(C... newObject);
    public Collection<C> addObjectsAt(int index, C... newObject) throws CollectionException;
    public Collection<C> addCollections(Collection<? extends C>... newCollections);
    public Collection<C> addCollectionsAt(int index, Collection<? extends C>... newCollections) throws CollectionException;

    public Collection<C> removeLast(int num);
    public Collection<C> removeLast();
    public Collection<C> removeLastFrom(int startIndex) throws CollectionException;
    public Collection<C> removeSublist(int startIndex, int stopIndex) throws CollectionException;
    public Collection<C> removeObjects(int... indeces) throws CollectionException;
    public Collection<C> removeInstances(C object, boolean equalsMethod);
    public Collection<C> removeNulls();
    public Collection<C> removeAll(boolean equalsMethod, Collection<C>... otherCollection);
    public Collection<C> removeAll();

    public Collection<C> replace(int index, C newObject) throws CollectionException;
    public Collection<C> replaceAll(C oldObject, C newObject, boolean equalsMethod);

    public Collection<C> sublistIntersect(boolean equals, Collection<C>... otherCollections);
    public Collection<C> sublistKeep(int startIndex, int stopIndex) throws CollectionException;
    public Collection<C> sublistFromIndeces(int... indeces) throws CollectionException;
    public Collection<C> sublistConsolidate(boolean equalsMethod);

    public Collection<C> orderReverse();
    public Collection<C> orderRandomize();
    public Collection<C> orderSwap(int firstIndex, int secondIndex) throws CollectionException;
    public Collection<C> orderShift(int amount);

    public Collection<C> orderAscending();
    public Collection<C> orderDescending();
    public C getMode(boolean equalsMethod);
    public C getMax();
    public C getMin();
    public C getLowerMedian();
    public C getUpperMedian();

    //THE FOLLOWING METHODS ARE ONLY FOR USE WITH NUMBER OBJECTS AND THROW
    //UnsupportedOperationException WHEN ATTEMPTED WITH OTHER OBJECTS
    public double getMean() throws UnsupportedOperationException;
    public double getSum() throws UnsupportedOperationException;

}

/**
 * A mutable class for use as a generic holder for a group of objects of a defined class.
 *
 * <p>NOTE: use caution when using methods that have a boolean parameter <code>equalsMethod</code>.
 * Note that when <code>equalsMethod = true</code>, objects are compared using their <code>.equals(Object ?)</code>
 * methods and when <code>equalsMethod = false</code>, objects are compared
 * using the <code>==</code> operator. For proper use of methods when <code>equalsMethod = true</code>,
 * be sure that the object has well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods
 * that override those in the <code>Object</code> class
 *
 * <p>NOTE: when assigning objects to this collection, references to the exact object
 * that was assigned will be used. To achieve independence of copies, either
 * use a <code>.clone()</code> method when assigning objects, or use the <code>.createIndependence()</code> method
 * to detach all elements in this collection. These methods require that
 * objects implement the <code>Cloneable</code> interface and implement the <code>.clone()</code> method
 * that returns a deep copy of the object from which it is called
 *
 * <p>NOTE: this class is mutable: some methods provided are called as <code>public Collection&lt;C&gt;</code> methods,
 * but can be thought of as <code>void</code> methods. These methods, as noted in the annotations
 * alter the fields in the collection and then return the collection itself. This return of the parent collection is solely
 * for use with chaining of methods. One can call a series of methods in
 * one line, altering the root <code>Collection&lt;C&gt;</code> object when using the methods that
 * return the parent collection itself. It is possible to leave these commands alone,
 * or have a <code>Collection&lt;C&gt;</code> variable to receive the output. Here is an example
 * of how chaining of methods alters the root method:
 * <br><dir><code>Collection&lt;Number&gt; c = new Collection&lt;Number&gt;(new Integer(4), new Double(2.73));
 * <br>c.addObjects(new Double(1.3)).addCollections(c).removeLast(2);
 * <br>System.out.println(c);</code> prints: <code>"0) 4 \n 1) 2.73 \n 2) 1.3 \n 3) 4"
 * <br>System.out.println(c.sublistKeep(0, 2).getNumObjects());</code> prints: <code>"2"
 * <br>System.out.println(c);</code> prints: <code>"0) 4 \n 1) 2.73"</code></dir>
 * 
 * <p>If one wants to create a new <code>Collection&lt;C&gt;</code> object to receive the output from a chain of
 * methods without altering the root collection, one must first declare the new collection
 * as a copy of the old and then call the methods from the new. Example:
 * <br><dir><code>Collection&lt;Number&gt; c = new Collection&lt;Number&gt;(new Integer(4), new Double(1.2));
 * <br></code>(<code>Collection&lt;Number&gt; c2 = c.orderReverse(); </code>) will alter the contents of <code> c
 * <br></code>(<code>Collection&lt;Number&gt; c2 = c;
 * <br>c2.orderReverse()</code>) will also alter the contents of <code> c
 * <br></code>(<code>Collection&lt;Number&gt; c2 = new Collection&lt;Number&gt;(c);
 * <br>c2.orderReverse()</code> will only alter the contents of <code> c2</code>, but not <code> c</code>.</dir>
 *
 * <p>Also note that any alterations made to objects accessed through methods such as <code>getObjectAt(int)</code>, will affect
 * the object in the collection since it is the reference address that is accessed.
 * Here is an example of how the object accessor method getObjectAt(int) returns only
 * the address of the object (let <code>Matrix</code> be some mutable class):
 * <br><dir><code>Collection&lt;Matrix&gt; c = new Collection&lt;Matrix&gt;(new Matrix(4), new Matrix(3.2);
 * <br>System.out.println(c); </code> prints: <code> "0) 4 \n 1) 3.2"
 * <br>c.getObjectAt(1).add(4);
 * <br>System.out.println(c); </code> prints: <code> "0) 4 \n 1) 7.2"
 * <br>c.getObjectAt(1).clone().subtract(4);
 * <br>System.out.println(c); </code> prints: <code> "0) 4 \n 1) 7.2"</code></dir>
 *
 * <p>NOTE: this class provides methods <code>.makeFinal()</code> and <code>.limitSize(int)</code> to control the
 * structure of this collection. If this collection is made final, the reference
 * addresses are final. No reference addresses can be replaced, removed, added. Beware
 * that the objects in this collection can still be altered outside the collection (see
 * above example code). A <code>CollectionIsFinalException</code> exception will be thrown when attempting to
 * alter a final collection. If attempting to add more objects than allowed in a
 * collection, no exception will be thrown - the collection will instead simply
 * not add excess objects and do so without notification.
 *
 * <p>NOTE: in method annotations, watch for information about the mutation of the
 * collection. Methods will be marked with any of four labels unless it is a non-mutating
 * method: Collection Mutator, Object Mutator, Possible Collection Mutator, Possible Object Mutator.
 * <dir><u>Collection Mutator</u> methods alter the order, or length of the collection
 * <br><u>Object Mutator methods</u> alter objects in this collection in some way
 * <br><u>Possible Collection Mutator</u> methods may alter the order or length of the collection
 * under certain circumstances
 * <br><u>Possible Object Mutator</u> methods may alter the objects in this collection in some way
 * under certain circumstances. Usually these methods return reference addresses of
 * objects, allowing the caller to alter the objects referenced by this collection</dir>
 *
 * <p>This class implements <code>Iterable&lt;C&gt;</code>, which supports use of simple for-each syntax: (To navigate the collection more flexibly, use a simple for loop)
 * <br><dir><code>for (Number num : new Collection&lt;Number&gt;(...)) { }</code></dir>
 *
 * @author Kapil Easwar
 * @date July 2009
 * @param <C> the class of objects which this collection will hold. This collection
 * can hold objects of subclasses of C
 */
public class Collection<C> implements GenericCollection<C>, Iterable<C>, Cloneable {
    /**
     * This is the array of objects. This array is only expanded, never reduced in size.
     * The capacity of this collection object is the length of this array. If
     * new space is needed, twice the amount of required space is added by copying
     * this array into an array of greater size. If an object is removed, only
     * the counter is reduced
     * @see #hasLessThan(int) 
     * @see #increaseSize(int) 
     * @see #num
     */
    protected C[] collection;
    /**
     * This is the number of objects in the array. When objects are added, this
     * is increased to represent the correct number of objects. When objects are
     * reduced, nothing is removed from the array "collection", but rather this
     * number is reduced. Removed objects can in theory be referenced from inside (though they should not)
     * this class, but public methods throw <code>IndexOutOfBoundsException</code> if this
     * is attempted from outside
     */
    protected int num;

    /**
     * This is the state of this collection. It determines whether or not changes
     * can be made to this collection. It is initialized to <code>false</code>, but
     * changed to <code>true</code> once <code>.setFinal()</code> has been called
     * @see #setFinal()
     * @see my.groups.CollectionIsFinalException
     */
    protected boolean isFinal;
    /**
     * This is the maximum size of the collection. It is initialized to <code>-1</code>, which
     * is interpreted to mean unlimited size. It determines the maximum number that <code>num</code>
     * is allowed to be, and is set once <code>.setSizeLimit(int)</code> is called
     * @see #num
     * @see #setSizeLimit(int) 
     */
    protected int sizeLimit;

    /**
     * This method is used internally to determine whether there is enough space
     * in the array <code>collection</code> to store a given number of elements
     * @param required the number of elements that must be stored
     * @return <code>true</code> if there is not enough space in the array <code>collection</code>
     * to hold the given number of objects, <code>false</code> if there is
     * @see #collection
     */
    protected boolean hasLessThan(int required) {
        return collection.length - getNumObjects() < required;
    }
    /**
     * This method is used internally to increase the capacity of this collection
     * by creating a new <code>C[]</code> array that increases the capacity by the given amount
     * and copies over existing objects into the corresponding locations, and
     * then assigning the array <code>collection</code> to the new array
     * @param ds the number of empty slots to add to this collection to increase
     * its capacity
     * @see #collection
     */
    protected void increaseSize(int ds) {
        C[] temp = (C[]) new Object[getNumObjects() + ds];
        for (int i=0; i<getNumObjects(); i++) {
            temp[i] = getObjectAt(i);
        }
        collection = temp;
    }
    private void throwOutOfBoundsLess(int... indeces) throws CollectionException {
        for (int i=0; i<indeces.length; i++) {
            if (indeces[i] < 0 || indeces[i] > getNumObjects()) {
                if (!isEmpty()) {
                    throw new CollectionException("Requested index: " + indeces[i] + ", max index: " + getNumObjects());
                } else {
                    throw new CollectionException("Requested index: " + indeces[i] + ", collection is empty");
                }
            }
        }
    }
    private void throwOutOfBoundsEqual(int... indeces) throws CollectionException {
        for (int i=0; i<indeces.length; i++) {
            if (indeces[i] < 0 || indeces[i] >= getNumObjects()) {
                throw new CollectionException("Requested index: " + indeces[i] + ", max index: " + (getNumObjects() - 1));
            }
        }
    }
    /**
     * This method is used internally as a simple way to throw a <code>CollectionIsFinalException</code>.
     * This should be called at the beginning of any method that will alter
     * the contents of this collections
     * @throws my.groups.CollectionIsFinalException if and only if the collection is final
     */
    protected void throwFinalCollectionException() throws CollectionIsFinalException {
        if (isFinal()) {
            throw new CollectionIsFinalException();
        }
    }
    /**
     * This method is used internally as a simply way to correct this collection for size.
     * It does not do anything unless a size limit has been enforced and exceeded, in which case
     * it changes the number of objects to the maximum size. This method should be
     * called at the end of any method that adds objects to this collection, after
     * the field <code>num</code> has been increased by the method itself.
     * <p><u>Collection Mutator</u> - this method will reduce the length of this
     * collection to the maximum number if the size limit has been exceeded
     * @see #num
     */
    protected void alterSizeToRestriction() {
        if (getSizeLimit() > -1 && num < getSizeLimit()) {
            num = getSizeLimit();
        }
    }

    /**
     * Constructor creates a new empty collection that accepts objects of a defined
     * class. Ex: <code>Collection&lt;Number&gt;</code> will create a collection of <code>Number</code>
     * objects. The default class is <code>Object</code>.
     */
    public Collection() {
        collection = (C[]) new Object[25];
        num = 0;

        isFinal = false;
        sizeLimit = -1;
    }

    /**
     * Constructor creates a new collection then adds the passed objects to the collection.
     * Ex: <code>Collection&lt;Number&gt;</code> will create a collection of <code>Number</code>
     * objects. The default class is <code>Object</code>.
     * @param objects the objects that will be initialized into the list in the order
     * that they are passed
     */
    public Collection(C... objects) {
        this();
        addObjects(objects);
    }

    /**
     * Constructor creates a new collection, then adds all objects of the passed collections to
     * this collection. Ex: <code>Collection&lt;Number&gt;</code> will create a collection of <code>Number</code>
     * objects. The default class is <code>Object</code>.
     * @param collections the objects that will be initialized into the list in the order
     * that they are passed
     */
    public Collection(Collection<? extends C>... collections) {
        this();
        addCollections(collections);
    }

    /**
     * This method is used to ensure that not changes can be made to this collection.
     * Also sets the size limit to the number of objects currently in this collection
     * <p><u>Collection Mutator</u> - while this method is not itself a mutator, it
     * will prevent many other Collection Mutator methods from altering this collection
     */
    public void setFinal() {
        isFinal = true;
        setSizeLimit(getNumObjects());
    }

    /**
     * This method is used to determine whether changes can be made to this collection
     * @return <code>true</code> if this collection is final, <code>false</code> otherwise
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * This method is used to limit the number of objects this collection can hold.
     * NOTE: multiple copies of elements will be treated as different elements, and
     * no exception will be thrown if more objects are attempted to be put in - they
     * will simply be ignored and no notification will be given
     * <p><u>Collection Mutator</u> - this method will not allow more than a given
     * number of objects to be added to this collection
     * @param maxSize the number of objects this collection can hold
     * @throws java.lang.CollectionException if maxSize is less than the number
     * of objects currently in the collections
     */
    public void setSizeLimit(int maxSize) throws CollectionException {
        if (maxSize < 0) {
            throw new CollectionException("Size limit cannot be less than zero");
        } else if (maxSize < getNumObjects()) {
            throw new CollectionException("Size limit cannot be less than the current number of objects");
        }
        sizeLimit = maxSize;
    }

    /**
     * This method is used to get the maximum number of objects this collection
     * can hold, as defined by the method <code>.limitSize(int)</code>
     * @return the maximum number of objects this collection can hold, or <code>-1</code>
     * if there is so size limit to this collection
     */
    public int getSizeLimit() {
        return sizeLimit;
    }

    /**
     * This method is used to determine whether or not the contents of this collection
     * are equal to the contents of the passed collection using the objects' <code>.equals(Object ?)</code>
     * method. This method calls <code>.equals(otherObject, true)</code> to determine equality
     * @param otherObject an object to compare to this collection for equality
     * @return <code>true</code> if the passed object is a <code>Collection</code> object,
     * both collections have equal number of objects and each object is equal to
     * the corresponding object in the passed collection as defined by its <code>.equals(Object ?)</code>
     * method
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject.getClass() != getClass()) {
            return false;
        }
        return equals((Collection) otherObject, true);
    }
    /**
     * @return 5 for all <code>Collection</code> objects
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * This method is used to compare this collection with another to check their equality.
     * For equality, each collection must be of the same length and each pair of objects in
     * corresponding indeces must be equal. The comparison that determines equality
     * between individual elements is controlled by the parameter <code>equalsMethod</code>.
     * @param otherCollection the collection to which this collection will be compared
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return <code>true</code> if the collections are equal by the above criteria, <code>false</code> if they are not
     */
    public boolean equals(Collection otherCollection, boolean equalsMethod) {
        if (getNumObjects() != otherCollection.getNumObjects()) {
            return false;
        } else if (equalsMethod) {
            return equalsTrue(otherCollection);
        } else {
            return equalsFalse(otherCollection);
        }
    }
        private boolean equalsTrue(Collection otherCollection) {
            for (int i=0; i<getNumObjects(); i++) {
                if (!getObjectAt(i).equals(otherCollection.getObjectAt(i))) {
                    return false;
                }
            }
            return true;
        }
        private boolean equalsFalse(Collection otherCollection) {
            for (int i=0; i<getNumObjects(); i++) {
                if (getObjectAt(i) != otherCollection.getObjectAt(i)) {
                    return false;
                }
            }
            return true;
        }

    /**
     * This method is used to get the string representation of this collection. Each
     * object is printed with its index on separate lines
     * @return a string representation of this collection
     */
    @Override
    public String toString() {
        String out = "";
        for (int i=0; i<getNumObjects(); i++) {
            out += i + ") " + getObjectAt(i) + "\n";
        }
        return out;
    }

    /**
     * This method is used to extract an array version of the collection. NOTE:
     * this array is NOT independent of this collection. Alterations made to
     * the returned <code>C[]</code> array will alter the contents of this collection.
     * To get an array whose elements are independent (deep) copies of the corresponding
     * elements in this collection, call <code>.createIndependence()</code> first.
     * @return an array of which each element is a shallow copy of the corresponding element
     * of this collection
     */
    @Override
    public C[] toArray() {
        C[] out = (C[]) new Object[getNumObjects()];
        for (int i=0; i<getNumObjects(); i++) {
            out[i] = getObjectAt(i);
        }
        return out;
    }

    /**
     * This method is used to create a new collection that is an independent
     * copy of this collection. Calls the <code>.createIndependence()</code> method to create
     * independent copies of the elements (though this may not happen, see <code>.createIndependence()</code>
     * method notes). NOTE: the returned collection may not be a fully independent
     * version of this, if any of the elements does not support cloning. To
     * test independence of this collection from another collection,
     * use the <codee>.isIndependentOf(Collection&lt;C&gt;)</code> method.
     * NOTE: the collection returned by this method will not be set to final,
     * and will have no size limit
     * @return a new (hopefully) independent copy of this collection
     * @see #createIndependence()
     * @see #isIndependentOf(my.groups.Collection) 
     */
    @Override
    public Collection<C> clone() {
        Collection<C> out = new Collection<C>(this);
        out.createIndependence();
        return out;
    }

    /**
     * This method is used to allow <code>for-each</code> syntax, creating an iterator over
     * this collection
     * <p><u>Possible Object Mutator</u> - this method allows use of <code>for-each</code>
     * syntax, which may allow the user to get the reference address of an object
     * and alter it
     * @return an iterator that starts at index <code>0</code>
     */
    public Iterator<C> iterator() {
        return new Iterator<C>() {
            private int index = -1;

            /**
             * This method is used to determine whether an  index is the last
             * in the collection
             */
            public boolean hasNext() {
                return index < getNumObjects() - 1;
            }

            /**
             * This method is used to get the next object in the list, then
             * increase the index of the iterator
             */
            public C next() {
                index++;
                return getObjectAt(index);
            }

            /**
             * This method does nothing because it is likely to not be called
             * for this class
             */
            public void remove() {
                //NOT IMPLEMENTED... THIS SHOULD NOT COME UP
            }
        };
    }

    /**
     * This method is used to "detach" elements of this collection, reassigning each
     * index a new, independent (deep) copy of the object originally at that index.
     * Assigns a clone of each element's reference object and assigns it to
     * the same index so that no two elements reference the same object. NOTE:
     * this requires that all elements of this collection have well-implemented
     * <code>.clone()</code> methods. If an object does not support cloning, then its reference
     * will not change. Even if one or more objects does not support cloning, the
     * method will continue through all other elements in this collection and
     * clone them if possible
     * <p><u>Object Mutator</u> - this method will replace all existing objects
     * with (hopefully) deep copies of themselves, and will thus detach these
     * objects from all others
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> createIndependence() {
        throw new UnsupportedOperationException("not supported yet");
        //return this;
    }

    /**
     * This method is used to determine whether or not any of the elements of
     * this collection reference any of the elements of the passed collection.
     * If even one element of this collection references an element of the passed
     * collection, then the collections are not independent
     * @param otherCollection the collection to be tested against this collection
     * for independence
     * @return <code>true</code> if none of the elements in either list reference elements in
     * the other, <code>false</code> if at least one does
     */
    public boolean isIndependentOf(Collection otherCollection) {
        for (int i=0; i<getNumObjects(); i++) {
            if (otherCollection.isContains(getObjectAt(i), false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is used to determine whether there are no objects in this
     * collection
     * @return <code>true</code> if there are no objects in this collection
     * <code>false</code> otherwise
     */
    @Override
    public boolean isEmpty() {
        return getNumObjects() == 0;
    }

    /**
     * This method is used to determine whether or not the passed object exists
     * in this collection. The comparison between elements of this collection
     * and the passed object that determines equality is controlled by the
     * parameter <code>equalsMethod</code>
     * @param toFind the object whose existence in this collection is to be tested
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return <code>true</code> if the object exists somewhere in this collection, <code>false</code> otherwise
     */
    public boolean isContains(C toFind, boolean equalsMethod) {
        return this.getNumInstances(toFind, equalsMethod) > 0;
    }

    /**
     * This method is used to determine whether or not any objects exist in this collection
     * whose <code>.toString()</code> methods are equal to the passed string
     * @param toString the string that will be compared to each element's <code>.toString()</code>
     * method using <code>String.equals(String)</code>
     * @return <code>true</code> if any object's <code>.toString()</code> methods is equal to the passed string,
     * <code>false</code> if no objects match this criteria
     */
    public boolean isContainsString(String toString) {
        return this.getNumInstancesString(toString) > 0;
    }

    /**
     * This method is to determine whether this collection, in its current order, exists
     * as an uninterrupted unit within another collection. The comparison of elements
     * that determines the equality of objects is controlled by the parameter <code>equalsMethod</code>
     * @param otherCollection the collection that may contain this collection
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return <code>true</code> if this collection exists uninterrupted in its entirety within the
     * requested collection, <code>false</code> otherwise
     * @throws java.lang.CollectionException if this collection is empty
     */
    public boolean isOrderedSubsetOf(Collection otherCollection, boolean equalsMethod) throws CollectionException {
        if (isEmpty()) {
            throw new CollectionException("cannot call Collection.isOrderedSubsetOf(Collection) from an empty collection");
        }
        Collection<C> sub;
        for (int i=0; i<=otherCollection.getNumObjects() - getNumObjects(); i++) {
            sub = new Collection<C>(otherCollection);
            sub.sublistKeep(i, i + getNumObjects());
            if (equals(sub, equalsMethod)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to determine whether each element of this collection is
     * present in the passed collection. If there are multiple copies of one element
     * in this collection, it only requires that one copy exists in the passed
     * collection. The comparison that determines the equality of two elements
     * is controlled by the parameter <code>equalsMethod</code>
     * @param otherCollection the collection to be tested as a superset of this collection
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return <code>true</code> if this collection is a subset of the passed collection, <code>false</code> otherwise
     */
    public boolean isSubsetOf(Collection otherCollection, boolean equalsMethod) {
        for (int i=0; i<getNumObjects(); i++) {
            if (!otherCollection.isContains(getObjectAt(i), equalsMethod)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is used to get the number of elements in the collection
     * @return the number of elements in the collection
     */
    public int getNumObjects() {
        return num;
    }

    /**
     * This method is used to get the object that is at a given index
     * @param index the index whose object will be returned
     * @return the object at the requested index. May return <code>null</code> if
     * the object at the requested index was so defined.
     * @throws java.lang.CollectionException if the collection does not
     * have an element at that index. It is not thrown if the element is <code>null</code>,
     * only if the requested index is less than zero or greater than of equal to
     * the number of objects in the collection
     * <p><u>Possible Object Mutator</u> - this method will return the reference
     * address of a certain object, allowing the caller to alter that object
     */
    public C getObjectAt(int index) throws CollectionException {
        throwOutOfBoundsEqual(index);
        return collection[index];
    }

    /**
     * This method is used to find the number of instances of a given object
     * in this collection. The comparison of each element to the passed object
     * is controlled by the parameter "equalsMethod"
     * @param toFind the object whose number of instances will be found
     * @param equalsMethod defines how the objects will be compared. If equalsMethod = true,
     * then elements are compared using the element's .equals(..) method; if equalsMethod = false,
     * then objects are compared using the == operator. NOTE: make sure the object has
     * well-implemented .equals(Object ?) and .hashCode() methods that override those in the Object class
     * @return the number of instances of the requested object that exist in this
     * collection
     */
    public int getNumInstances(C toFind, boolean equalsMethod) {
        if (equalsMethod) {
            return getNumInstancesOfTrue(toFind);
        } else {
            return getNumInstancesOfFalse(toFind);
        }
    }
        private int getNumInstancesOfTrue(C toFind) {
            int out = 0;
            for (int i=0; i<getNumObjects(); i++) {
                if (getObjectAt(i).equals(toFind)) {
                    out++;
                }
            }
            return out;
        }
        private int getNumInstancesOfFalse(C toFind) {
            int out = 0;
            for (int i=0; i<getNumObjects(); i++) {
                if (getObjectAt(i) == toFind) {
                    out++;
                }
            }
            return out;
        }

    /**
     * This method is used to get the index of a certain object. The comparison of
     * each element to the passed object is controlled by the parameter <code>equalsMethod</code>
     * @param toFind the object whose first index is to be found
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return the index of the first instance of the requested object if it is found, and
     * <code>-1</code> if it is not
     */
    public int getIndex(C toFind, boolean equalsMethod) {
        return getIndexFrom(toFind, 0, equalsMethod);
    }

    /**
     * This method is used to get the index of a certain object starting at
     * a given index. The comparison of each element to the passed object is
     * controlled by the parameter <code>equalsMethod</code>
     * @param toFind the object whose next index is to be found
     * @param startIndex the index at which the search will start
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return the first index of the requested object after the start index if the object
     * is found, <coce>-1</code> if not
     * @throws java.lang.CollectionException if <code>startIndex</code> is less than zero
     * or greater than or equal to the number of objects in this collection
     */
    public int getIndexFrom(C toFind, int startIndex, boolean equalsMethod) throws CollectionException {
        throwOutOfBoundsEqual(startIndex);
        if  (equalsMethod) {
            return getIndexOfTrue(toFind, startIndex);
        } else {
            return getIndexOfFalse(toFind, startIndex);
        }
    }
        private int getIndexOfTrue(C toFind, int start) {
            for (int i=start; i<getNumObjects(); i++) {
                if (getObjectAt(i).equals(toFind)) {
                    return i;
                }
            }
            return -1;
        }
        private int getIndexOfFalse(C toFind, int start) {
            for (int i=start; i<getNumObjects(); i++) {
                if (getObjectAt(i) == toFind) {
                    return i;
                }
            }
            return -1;
        }

    /**
     * This method is used to get the index of the last instance of the passed object
     * in the collection. The comparison of each element to the passed object is
     * controlled by the parameter <code>equalsMethod</code>
     * @param toFind the object whose last index will be return
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return the index of the last instance of the requested object if the object
     * is found, <code>-1</code> if it is not
     */
    public int getLastIndex(C toFind, boolean equalsMethod) {
        if (equalsMethod) {
            return getLastIndexTrue(toFind);
        } else {
            return getLastIndexFalse(toFind);
        }
    }
        private int getLastIndexTrue(C toFind) {
            for (int i=getNumObjects() - 1; i>=0; i--) {
                if (getObjectAt(i).equals(toFind)) {
                    return i;
                }
            }
            return -1;
        }
        private int getLastIndexFalse(C toFind) {
            for (int i=getNumObjects() - 1; i>=0; i--) {
                if (getObjectAt(i) == toFind) {
                    return i;
                }
            }
            return -1;
        }

    /**
     * This method is used to get a list of all the indeces of the passed object
     * in the collection. The comparison of each element to the passed object is
     * controlled by the parameter <code>equalsMethod</code>
     * @param toFind the object whose indeces will be returned
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return and integer array whose elements are the indeces of the object in
     * this collection if the object is found, and returns and integer array of length
     * zero if the object is not found
     */
    public int[] getIndeces(C toFind, boolean equalsMethod) {
        if (equalsMethod) {
            return getIndecesOfTrue(toFind);
        } else {
            return getIndecesOfFalse(toFind);
        }
    }
        private int[] getIndecesOfTrue(C toFind) {
            int[] out = new int[getNumInstances(toFind, true)];
            int index = 0;
            for (int i=0; i<getNumObjects(); i++) {
                if (getObjectAt(i).equals(toFind)) {
                    out[index] = i;
                    index++;
                }
            }
            return out;
        }
        private int[] getIndecesOfFalse(C toFind) {
            int[] out = new int[getNumInstances(toFind, false)];
            int index = 0;
            for (int i=0; i<getNumObjects(); i++) {
                if (getObjectAt(i) == toFind) {
                    out[index] = i;
                    index++;
                }
            }
            return out;
        }

    /**
     * This method is used to access the object whose <code>.toString()</code> method is
     * equal to the passed string
     * @param toString the string that will be compared to each element's <code>.toString()</code>
     * method using <code>String.equals(String)</code>
     * @return the first element in this collection whose <code>.toString()</code>
     * method is equal to the passed string, or <code>null</code> if none is found
     * <p><u>Possible Object Mutator</u> - this method will return the reference
     * address of a certain object, allowing the caller to alter that object
     */
    public C getObjectString(String toString) {
        return getObjectStringFrom(toString, 0);
    }

    /**
     * This method is used to get the first object in this collection, starting
     * from the passed index, whose <code>.toString()</code> method is equal to the passed string
     * @param toString the string that will be compared to each element's <code>.toString()</code>
     * method using <code>String.equals(String)</code>
     * @param startIndex the index at which the first index will start
     * @return the first object in the collection, starting from the given index,
     * whose <code>.toString()</code> method is equal to the passed string, or <code>null</code> if no
     * object is found
     * @throws java.lang.CollectionException if the startIndex is less than zero
     * or greater than or equals to the number of objects in this collection
     * <p><u>Possible Object Mutator</u> - this method will return the reference
     * address of a certain object, allowing the caller to alter that object
     */
    public C getObjectStringFrom(String toString, int startIndex) throws CollectionException {
        throwOutOfBoundsEqual(startIndex);
        for (int i=startIndex; i<getNumObjects(); i++) {
            if (getObjectAt(i).toString().equals(toString)) {
                return getObjectAt(i);
            }
        }
        return null;
    }

    /**
     * This method is used to determine the number of elements in this collection
     * whose <code>.toString()</code> methods are equal to the passed string
     * @param toString the string to which each element's <code>.toString()</code> methods will
     * be compared using <code>String.equals(String)</code>
     * @return the number of elements that match this criteria
     */
    public int getNumInstancesString(String toString) {
        int out = 0;
        for (int i=0; i<getNumObjects(); i++) {
            if (getObjectAt(i).toString().equals(toString)) {
                out++;
            }
        }
        return out;
    }

    /**
     * This method is used to get the index of the first element in this collection
     * whose <code>.toString()</code> method is equal to the passed string
     * @param toString the string that will be compared to each element's <code>.toString()</code>
     * method using <code>String.equals(String)</code>
     * @return the index of the first element in this collection whose <code>.toString()</code>
     * method is equal to the passed string, or <code>-1</code> if the object is not found
     */
    public int getIndexString(String toString) {
        return getIndexStringFrom(toString, 0);
    }

    /**
     * This method is used the get the index of the first element in this collection
     * starting from the given start index whose <code>.toString()</code> method is equal to
     * the passed string
     * @param toString the string that will be compared to each element's <code>.toString()</code>
     * method using <code>String.equals(String)</code>
     * @param startIndex the index at which the search will start
     * @return the index of the first element in this collection whose <code>.toString()</code>
     * method is equal tot he passed string, or <code>-1</code> if the object is not found
     * @throws java.lang.CollectionException if <code>startIndex</code> is less than
     * zero or greater than or equal to the number of objects in this collection
     */
    public int getIndexStringFrom(String toString, int startIndex) throws CollectionException {
        this.throwOutOfBoundsEqual(startIndex);
        for (int i=0; i<getNumObjects(); i++) {
            if (getObjectAt(i).toString().equals(toString)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method is used to get the index of the last object whose <code>.toString()</code>
     * method is equal to the passed string
     * @param toString the string with which each element's <code>.toString()</code> method
     * is compared using <code>String.equals(String)</code>
     * @return the index of the last object that matches this criteria if one
     * is found, <code>-1</code> if none is found
     */
    public int getLastIndexString(String toString) {
        for (int i=getNumObjects() - 1; i>=0; i--) {
            if (getObjectAt(i).toString().equals(toString)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method is used to get an integer array of the indeces of the objects
     * whose <code>.toString()</code> methods are equal to the passed string
     * @param toString the string that is compared to each element's <code>.toString()</code>
     * method using <code>String.equals(String)</code>
     * @return an integer array of all the indeces of these objects, which is
     * of length <code>0</code> if no objects that match this criteria exist
     */
    public int[] getIndecesString(String toString) {
        int[] out = new int[getNumInstancesString(toString)];
        int index = 0;
        for (int i=0; i<getNumObjects(); i++) {
            if (getObjectAt(i).toString().equals(toString)) {
                out[index] = i;
                index++;
            }
        }
        return out;
    }

    /**
     * This method is used to append a list of new objects to the end of this collection.
     * Objects are assigned at certain indeces with the <code>=</code> operator,
     * so it is the reference addresses of the passed objects that will be copied, not
     * new independent deep copies.
     * <p><u>Collection Mutator</u> - this method will add objects to this collection
     * @param newObjects the list of objects that will be added to the end in the
     * order that they are passed. Must be of the same class as is defined for objects
     * of this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> addObjects(C... newObjects) {
        throwFinalCollectionException();
        addObjectsAt(getNumObjects(), newObjects);
        return this;
    }

    /**
     * This method is used to insert a list of objects at a given index in this
     * collection in the order they are passed. The first object will be at index
     * <code>index</code>. Objects are assigned with the <code>=</code> operator,
     * so it is the reference addresses of the passed objects that will be copied, not
     * new independent deep copies.
     * <p><u>Collection Mutator</u> - this method will add objects to this collection
     * @param index the index which the first passed object will subsequently occupy
     * @param newObjects the list of objects to be inserted, the first of which
     * will occupy the given index
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @throws java.lang.CollectionException if the given index is less than
     * zero or greater than the number of elements in this collection
     */
    public Collection<C> addObjectsAt(int index, C... newObjects) throws CollectionException {
        throwFinalCollectionException();
        throwOutOfBoundsLess(index);
        if (hasLessThan(newObjects.length)) {
            increaseSize(newObjects.length * 2);
        }
        for (int i=getNumObjects() - 1 + newObjects.length; i>= index + newObjects.length; i--) {
            collection[i] = getObjectAt(i - newObjects.length);
        }
        for (int i=index; i<index + newObjects.length; i++) {
            collection[i] = newObjects[i - index];
        }
        num += newObjects.length;
        alterSizeToRestriction();
        return this;
    }

    /**
     * This method is used to append objects of various other collections to the end
     * of this collection in the order that they appear in the passed collections.
     * Objects are assigned with the <code>=</code> operator, so it is the reference
     * addresses of the passed objects that will be copied, not new independent
     * copies.
     * <p><u>Collection Mutator</u> - this method will add objects to this collection
     * @param newCollections the collections whose objects will be appended to
     * the end of this collection. Must be collections that hold objects of the same
     * class as this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> addCollections(Collection<? extends C>... newCollections) {
        throwFinalCollectionException();
        addCollectionsAt(getNumObjects(), newCollections);
        return this;
    }

    /**
     * This method is used to insert objects of various other collections to the
     * passed index in the order that they appear in the collections. The first
     * object of the first collection will be at index <code>index</code>. Objects
     * are assigned with the <code>=</code> operator, so it is the reference
     * addresses of the objects that are copied, not independent deep copies.
     * <p><u>Collection Mutator</u> - this method will add objects to this collection
     * @param index the index at which the first object will be inserted
     * @param newCollections the collections whose objects will be inserted into
     * this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @throws java.lang.CollectionException if the index is less than zero
     * or greater than the number of objects in this collection
     */
    public Collection<C> addCollectionsAt(int index, Collection<? extends C>... newCollections) throws CollectionException {
        throwFinalCollectionException();
        throwOutOfBoundsLess(index);
        int numObjs = 0;
        for (int i=0; i<newCollections.length; i++) {
            numObjs += newCollections[i].getNumObjects();
        }
        C[] objs = (C[]) new Object[numObjs];
        int oInd = 0;
        for (int i=0; i<newCollections.length; i++) {
            for (int j=0; j<newCollections[i].getNumObjects(); j++) {
                objs[oInd] = newCollections[i].getObjectAt(j);
                oInd++;
            }
        }
        addObjectsAt(index, objs);
        return this;
    }

    /**
     * This method is used to remove a given number of objects from the end
     * of this collection
     * <p><u>Collection Mutator</u> - this method will remove objects from this collection
     * @param num the number of objects to be removed from the end of this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> removeLast(int num) {
        throwFinalCollectionException();
        for (int i=0; i<num; i++) {
            removeLast();
        }
        return this;
    }

    /**
     * This method is used to remove the last object in this collection
     * <p><u>Collection Mutator</u> - this method will remove objects from this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> removeLast() {
        throwFinalCollectionException();
        if (!isEmpty()) {
            num--;
        }
        return this;
    }

    /**
     * This method is used to remove objects from the end of this collection,
     * starting from and including the object at index <code>startIndex</code>.
     * <p><u>Collection Mutator</u> - this method will remove objects from this collection
     * @param startIndex the index of the first object to be removed
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @throws java.lang.CollectionException if the startIndex is less than
     * zero or greater than or equal to the number of objects in this collection
     */
    public Collection<C> removeLastFrom(int startIndex) throws CollectionException {
        throwFinalCollectionException();
        throwOutOfBoundsEqual(startIndex);
        num = startIndex;
        return this;
    }

    /**
     * This method is used to remove a section from the middle of the collection
     * and close the gap by shifting all posterior objects forward. If <code>stopIndex</code>
     * is greater than the number of objects in the list, all objects after
     * and including the object at <code>startIndex</code> will be removed
     * <p><u>Collection Mutator</u> - this method will remove objects from this collection
     * @param startIndex the index of the first object to be removed
     * @param stopIndex the index where removal will stop. The object at this index
     * will not be removed
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @throws java.lang.CollectionException if either index is less than
     * zero or greater than the number of objects in this collection, or if
     * <code>startIndex</code> is greater than the <code>stopIndex</code>
     */
    public Collection<C> removeSublist(int startIndex, int stopIndex) throws CollectionException {
        throwFinalCollectionException();
        if (startIndex > stopIndex) {
            throw new CollectionException("stopIndex cannot be greater than startIndex");
        }
        if (stopIndex > getNumObjects()) {
            removeLastFrom(startIndex);
        } else {
            throwOutOfBoundsLess(stopIndex);
            int i;
            for (i=0; i<getNumObjects() - stopIndex; i++) {
                replace(startIndex + i, getObjectAt(stopIndex + i));
            }
            num -= stopIndex - startIndex;
        }
        return this;
    }

    /**
     * This method is used to remove objects at the given indeces, shifting all
     * posterior objects to forward. If one of the indeces is out of bounds, then
     * it will not be removed and no exception will be thrown.
     * <p><u>Collection Mutator</u> - this method will remove objects from this collection
     * @param indeces the indeces of the objects to be removed
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @throws java.lang.CollectionIsFinalException if this collection has been
     * set to final
     */
    public Collection<C> removeObjects(int... indeces) throws CollectionIsFinalException {
        throwFinalCollectionException();
        if (indeces.length == 0) {
            return this;
        }
        indeces = orderDescending(indeces);
        for (int index : indeces) {
            if (index < getNumObjects()) {
                for (int i = index; i < getNumObjects() - 1; i++) {
                    replace(i, getObjectAt(i + 1));
                }
                num--;
            }
        }
        return this;
    }
        private int[] orderDescending(int[] set) {
            int[] out = new int[set.length];
            int min = min(set);
            int index = 0;
            int[] max;
            for (int i=0; i<set.length; i++) {
                max = max(set);
                out[index] = max[0];
                index++;
                set[max[1]] = min;
            }
            return out;
        }
        private int min(int[] set) {
            int min = set[0];
            for (int i=0; i<set.length; i++) {
                if (set[i] < min) {
                    min = set[i];
                }
            }
            return min;
        }
        private int[] max(int[] set) {
            int[] max = {set[0], 0};
            for (int i=0; i<set.length; i++) {
                if (set[i] > max[0]) {
                    max[0] = set[i];
                    max[1] = i;
                }
            }
            return max;
        }

    /**
     * This method is used to remove all copies of the passed object. The comparison
     * of each element to the passed object is controlled by the parameter
     * <code>equalsMethod</code>
     * <p><u>Collection Mutator</u> - this method will remove objects from this collection
     * @param object the object whose copies will be removed
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> removeInstances(C object, boolean equalsMethod) {
        throwFinalCollectionException();
        removeObjects(getIndeces(object, equalsMethod));
        return this;
    }

    /**
     * This method is used to remove any objects that are <code>null</code> in this collection.
     * The surrounding objects are then reconnected by being shifted to leave no gaps
     * <p><u>Collection Mutator</u> - this method will remove <code>null</code> references
     * from this collection, decreasing its length
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> removeNulls() {
        throwFinalCollectionException();
        removeInstances(null, false);
        return this;
    }

    /**
     * This method is used to remove all copies of objects in the passed collections.
     * This can be thought of the subtraction of a set (though these sets can
     * have multiple copies of elements). The comparison of elements that determines
     * whether or not an object of one set is in another is controlled by
     * the parameter "equalsMethod"
     * <p><u>Collection Mutator</u> - this method will remove objects from this collection
     * @param equalsMethod defines how the objects will be compared. If equalsMethod = true,
     * then elements are compared using the element's .equals(..) method; if equalsMethod = false,
     * then objects are compared using the == operator. NOTE: make sure the object has
     * well-implemented .equals(Object ?) and .hashCode() methods that override those in the Object class
     * @param otherCollections the other collections whose objects will be removed
     * from this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> removeAll(boolean equalsMethod, Collection<C>... otherCollections) {
        throwFinalCollectionException();
        for (int i=0; i<otherCollections.length; i++) {
            for (int j=0; j<otherCollections[i].getNumObjects(); j++) {
                removeInstances(otherCollections[i].getObjectAt(j), equalsMethod);
            }
        }
        return this;
    }

    /**
     * This method is used to remove all elements of this collection so that
     * there are zero objects in this collection
     * <p><u>Collection Mutator</u> - this method will remove everything from this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> removeAll() {
        throwFinalCollectionException();
        num = 0;
        return this;
    }

    /**
     * This method is used to store (overwrite) an object in an already occupied index,
     * deleting rid of the previous object. The new object is assigned using the <code>=</code>
     * operator, so it is the reference address of the object that will be assigned,
     * not an independent deep copy.
     * <p><u>Object Mutator</u> - this method will replace one object with another
     * @param index the index which the new object will occupy
     * @param newObject the object to overwrite the existing object at the given index
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @throws java.lang.CollectionException if the index is less than zero
     * or greater than or equal to the number of objects in this collection
     */
    public Collection<C> replace(int index, C newObject) throws CollectionException {
        throwFinalCollectionException();
        throwOutOfBoundsEqual(index);
        collection[index] = newObject;
        return this;
    }

    /**
     * This method is used to replace all occurrences of the passed object with
     * the a new object. The comparison between elements of this collection
     * and <code>oldObject</code> is controlled by the parameter <code>equalsMethod</code>. NOTE: references
     * to the same object will be assigned to the old indeces. To achieve independence
     * with the new object, use the createIndependence() method
     * <p><u>Object Mutator</u> - this method will replace many objects in this collection
     * @param oldObject the old object whose occurrences will be replace by the
     * new object
     * @param newObject the new object that will replace the occurrences of the
     * old object
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @see #createIndependence() 
     */
    public Collection<C> replaceAll(C oldObject, C newObject, boolean equalsMethod) {
        throwFinalCollectionException();
        int[] indeces = getIndeces(oldObject, equalsMethod);
        for (int index : indeces) {
            replace(index, newObject);
        }
        return this;
    }

    /**
     * This method is used to modify this collection such that it is the intersection
     * of itself and each collection passed. NOTE: If there are multiple copies of a
     * certain object that is in all sets, only the first will be kept.
     * The comparison of elements that determines whether or not an object is in
     * all sets is controlled by the parameter <code>equalsMethod</code>
     * <p><u>Collection Mutator</u> - this method will remove objects from this collection
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> sublistIntersect(boolean equalsMethod, Collection<C>... otherCollections) {
        throwFinalCollectionException();
        Collection<C> intersection = new Collection<C>();
        boolean inAll;
        for (int i=0; i<getNumObjects(); i++) {
            inAll = true;
            loop: for (int j=0; j<otherCollections.length; j++) {
                if (!otherCollections[j].isContains(getObjectAt(i), equalsMethod)) {
                    inAll = false;
                    break loop;
                }
            }
            if (inAll) {
                intersection.addObjects(getObjectAt(i));
            }
        }
        intersection.sublistConsolidate(equalsMethod);
        collection = intersection.toArray();
        num = intersection.getNumObjects();
        return this;
    }

    /**
     * This method is used to keep only the elements in a certain range. It removes
     * all elements before and after the range
     * <p><u>Collection Mutator</u> - this method will shorten this collection
     * @param startIndex the index of the first object to be kept
     * @param stopIndex the index at which the sublist stops. The object at this
     * index will not be kept
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @throws java.lang.CollectionException if either index is less than zero or
     * greater than the number of objects in this collection, or if <code>startIndex</code>
     * is greater than <code>stopIndex</code>
     */
    public Collection<C> sublistKeep(int startIndex, int stopIndex) throws CollectionException {
        throwFinalCollectionException();
        throwOutOfBoundsLess(startIndex, stopIndex);
        if (startIndex > stopIndex) {
            throw new CollectionException("stopIndex cannot be greater than startIndex");
        }
        if (stopIndex < getNumObjects()) {
            removeLastFrom(stopIndex);
        }
        removeSublist(0, startIndex);
        return this;
    }

    /**
     * This method is used to keep only the elements in the given indeces and remove
     * all others. NOTE: If any of the passed indeces is out of bounds, then not exception
     * is thrown, the index will simply be ignored.
     * <p><u>Collection Mutator</u> - this method will shorten this collection
     * @param indeces the indeces of the objects to be kept
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @throws java.lang.CollectionIsFinalException if this collection is final
     */
    public Collection<C> sublistFromIndeces(int... indeces) throws CollectionIsFinalException {
        throwFinalCollectionException();
        Collection<C> temp = new Collection<C>();
        for (int index : indeces) {
            if (index < getNumObjects()) {
                temp.addObjects(getObjectAt(index));
            }
        }
        collection = temp.toArray();
        num = indeces.length;
        return this;
    }

    /**
     * This method is used to remove all copies of each object, so that there is
     * just one instance of the each object. The comparison of elements that determines
     * equality is controlled by the parameter <code>equalsMethod</code>
     * <p><u>Collection Mutator</u> - this method will shorten this collection
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> sublistConsolidate(boolean equalsMethod) {
        throwFinalCollectionException();
        Collection<C> bare = new Collection<C>();
        for (int i=0; i<getNumObjects(); i++) {
            if (!bare.isContains(getObjectAt(i), equalsMethod)) {
                bare.addObjects(getObjectAt(i));
            }
        }
        collection = bare.toArray();
        num = bare.getNumObjects();
        return this;
    }

    /**
     * This method is used to reverse the order of the elements in this collection
     * <p><u>Collection Mutator</u> - this method will change the order of this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> orderReverse() {
        throwFinalCollectionException();
        C[] temp = (C[]) new Object[getNumObjects()];
        for (int i=0; i<getNumObjects(); i++) {
            temp[getNumObjects() - 1 - i] = getObjectAt(i);
        }
        collection = temp;
        return this;
    }

    /**
     * This method is used to randomize the order of the elements in this collection
     * <p><u>Collection Mutator</u> - this method will change the order of this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> orderRandomize() {
        throwFinalCollectionException();
        C[] temp = (C[]) new Object[getNumObjects()];
        int index;
        for (int i=0; i<getNumObjects(); i++) {
            do {
                index = (int) (Math.random()*getNumObjects());
            } while (temp[index] != null);
            temp[index] = getObjectAt(i);
        }
        collection = temp;
        return this;
    }

    /**
     * This method is used to swap the objects in the given indeces
     * <p><u>Collection Mutator</u> - this method will change the order of this collection
     * @param firstIndex the index of the first object to be swapped
     * @param secondIndex the index of the second object to be swapped
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     * @throws java.lang.CollectionException if either index is less
     * than zero or greater than or equal to the number of objects in this collection
     */
    public Collection<C> orderSwap(int firstIndex, int secondIndex) throws CollectionException {
        throwFinalCollectionException();
        throwOutOfBoundsEqual(firstIndex, secondIndex);
        C temp = getObjectAt(firstIndex);
        replace(firstIndex, getObjectAt(secondIndex));
        replace(secondIndex, temp);
        return this;
    }

    /**
     * This method is used to move all objects in the collection a certain amount
     * to the right. The new index of a certain object will be that amount greater
     * than its old index (objects near the ends may wrap around to the other end
     * of the collection). A negative amount will shift the objects to the left
     * <p><u>Collection Mutator</u> - this method will change the order of this collection
     * @param amount the amount that the new index of a certain object will change
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> orderShift(int amount) {
        throwFinalCollectionException();
        C[] temp = (C[]) new Object[getNumObjects()];
        for (int i=0; i<getNumObjects(); i++) {
            temp[i] = getObjectAt(validIndex(i - amount));
        }
        collection = temp;
        return this;
    }
        private int validIndex(int index) {
            while (index < 0) {
                index += getNumObjects();
            }
            while (index >= getNumObjects()) {
                index %= getNumObjects();
            }
            return index;
        }

    /**
     * This method is used to order the objects in this collection by alphabetizing
     * the return strings of their <code>.toString()</code> methods. This will
     * also order numerical values in ascending numerical order (by analyzing the
     * numbers' string representations).
     * <p><u>Collection Mutator</u> - this method will change the order of this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> orderAscending() {
        throwFinalCollectionException();
        Collection<C> ordered = new Collection<C>();
        C temp;
        while (!isEmpty()) {
            temp = getMin();
            ordered.addObjects(temp);
            removeObjects(getIndex(temp, false));
        }
        addObjects(ordered.toArray());
        return this;
    }

    /**
     * This method is used to order the objects in this collection by reverse-alphabetizing
     * the return strings of their <code>.toString()</code> methods. This will
     * also order numerical values in descending numerical order (by analyzing the
     * numbers' string representations).
     * <p><u>Collection Mutator</u> - this method will change the order of this collection
     * @return the parent method. NOTE: alterations are made to the parent
     * collection itself, and this new altered collection is returned for use
     * with chaining of methods (see class annotations for details)
     */
    public Collection<C> orderDescending() {
        throwFinalCollectionException();
        Collection<C> ordered = new Collection<C>();
        C temp;
        while (!isEmpty()) {
            temp = getMax();
            ordered.addObjects(temp);
            removeObjects(getIndex(temp, false));
        }
        addObjects(ordered.toArray());
        return this;
    }

    /**
     * This method is used to get the object whose string value comes last
     * alphabetically (or greatest numerically).
     * <p><u>Possible Object Mutator</u> - this method will return the reference
     * address of an object, allowing the caller to alter that object
     * @return the last object whose string value comes last alphabetically.
     * If there are multiple objects with the same string value, returns the last
     * of them
     */
    public C getMax() {
        C temp = getObjectAt(0);
        for (int i=1; i<getNumObjects(); i++) {
            if (getObjectAt(i).toString().compareTo(temp.toString()) >= 0) {
                temp = getObjectAt(i);
            }
        }
        return temp;
    }

    /**
     * This method is used to get the object whose string value comes
     * first alphabetically (or least numerically).
     * <p><u>Possible Object Mutator</u> - this method will return the reference
     * address of an object, allowing the caller to alter that object
     * @return the first object whose string value comes first alphabetically.
     * If there are multiply objects with the same string value, returns
     * the first of them
     */
    public C getMin() {
        C temp = getObjectAt(0);
        for (int i=1; i<getNumObjects(); i++) {
            if (getObjectAt(i).toString().compareTo(temp.toString()) < 0) {
                temp = getObjectAt(i);
            }
        }
        return temp;
    }

    /**
     * This method is used to get the object with the most number of instances in
     * this collection. The comparison between elements of this set is controlled
     * by the parameter equalsMethod.
     * <p><u>Possible Object Mutator</u> - this method will return the reference
     * address of an object, allowing the caller to alter that object
     * @param equalsMethod defines how the objects will be compared. If <code>true</code>,
     * then elements are compared using the element's <code>.equals(Object ?)</code> method. If <code>false</code>,
     * then objects are compared using the <code>==</code> operator. NOTE: make sure the object has
     * well-implemented <code>.equals(Object ?)</code> and <code>.hashCode()</code> methods that override those in the <code>Object</code> class
     * @return the object with the most number of instances in this collection, or the first
     * object in this collection that has the maximum number of instances if there are
     * multiple objects that have the maximum number of instances. Example:
     * <br><dir><code>Collection&lt;Number&gt; c = new Collection&lt;Number&gt;(new Double(4.3), new Integer(2), new Double(1.3));
     * <br>System.out.println(c.getMode(true)); </code> prints: <code> "4.3" </code> because there is no definite mode
     * and 4.3 is the first number with 1 instance (the maxiumum number any object has)</dir>
     */
    public C getMode(boolean equalsMethod) {
        C temp = getObjectAt(0);
        int tempNum = getNumInstances(temp, equalsMethod);
        for (int i=1; i<getNumObjects(); i++) {
            if (getNumInstances(getObjectAt(i), equalsMethod) > tempNum) {
                temp = getObjectAt(i);
                tempNum = getNumInstances(temp, equalsMethod);
            }
        }
        return temp;
    }

    /**
     * This method is used to get the closest object before (less) than the
     * median. Example:
     * <br><dir><code>Collection c = new Collection(new String("a"), new String("b"), new String("d"), new String("e"));
     * <br>System.out.println(c.getLowerMedian()); </code> prints: <code> "b"
     * <br>System.out.println(c.getUpperMedian()); </code> prints: <code> "d"
     * <br>c.addObjects(new String("z"));
     * <br>System.out.println(c.getLowerMedian()); </code> prints: <code> "d"
     * <br>System.out.println(c.getUpperMedian()); </code> prints: <code> "d" </code></dir>
     * <br>As a rule, if <code>this.getNumObjects()</code> is odd, <code>this.getLowerMedian()</code>
     * equals <code>this.getUpperMedian()</code>
     * <p><u>Possible Object Mutator</u> - this method will return the reference
     * address of an object, allowing the caller to alter that object
     * @return the closest object less than the median of this collection, or the
     * median itself if it exists
     */
    public C getLowerMedian() {
        Collection<C> temp = new Collection<C>(this);
        temp.orderDescending();
        if (temp.getNumObjects() % 2 == 1) {
            return temp.getObjectAt((getNumObjects() - 1) / 2);
        } else {
            return temp.getObjectAt(getNumObjects() / 2);
        }
    }

    /**
     * This method is used to get the closest object after (greater) than the
     * median. Example:
     * <br><dir><code>Collection c = new Collection(new String("a"), new String("b"), new String("d"), new String("e"));
     * <br>System.out.println(c.getLowerMedian()); </code> prints: <code> "b"
     * <br>System.out.println(c.getUpperMedian()); </code> prints: <code> "d"
     * <br>c.addObjects(new String("z"));
     * <br>System.out.println(c.getLowerMedian()); </code> prints: <code> "d"
     * <br>System.out.println(c.getUpperMedian()); </code> prints: <code> "d" </code></dir>
     * <br>As a rule, if <code>this.getNumObjects()</code> is odd, <code>this.getUpperMedian()</code>
     * equals <code>this.getLowerMedian()</code>
     * <p><u>Possible Object Mutator</u> - this method will return the reference
     * address of an object, allowing the caller to alter that object
     * @return the closes object greater than the median of this collection, or the
     * median itself if it exists
     */
    public C getUpperMedian() {
        Collection<C> temp = new Collection<C>(this);
        temp.orderAscending();
        if (temp.getNumObjects() % 2 == 1) {
            return temp.getObjectAt((getNumObjects() - 1) / 2);
        } else {
            return temp.getObjectAt(getNumObjects() / 2);
        }
    }

    /**
     * This method is used to get the mean of a collection that holds numbers.
     * It will throw an exception if any of the objects cannot be cast to a Number.
     * @return the mean value of this collection, provided that this collection
     * holds numbers
     * @throws java.lang.UnsupportedOperationException if this collection holds
     * any elements that cannot be cast to Number objects
     */
    public double getMean() throws UnsupportedOperationException {
        return getSum() / getNumObjects();
    }

    /**
     * This method is used to get the sum of a collection that holds numbers.
     * It will throw an exception if any of the objects cannot be cast to a Number.
     * @return the sum of this collection as a primitive double
     * @throws java.lang.UnsupportedOperationException if this collection holds any
     * elements that cannot be cast to Number objects
     */
    public double getSum() throws UnsupportedOperationException {
        double out = 0;
        for (int i=0; i<getNumObjects(); i++) {
            try {
                out += ((Number) getObjectAt(i)).doubleValue();
            } catch (ClassCastException ex) {
                throw new UnsupportedOperationException("getMean() cannot be applied to this collection");
            }
        }
        return out;
    }

}
