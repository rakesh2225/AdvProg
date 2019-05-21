#include <time.h>

typedef struct data_struct {
   time_t time;
 
   /* string is of an arbitrary length */
   char* string;
} data_t;

/* inserts a "copy" of the data item at the end of the list */

/* if successful, returns 0 */
/* if unsuccessful, returns -1 and sets errno */
int adddata (data_t data);

/* copies the next item in the traversal of the list into
   a user-supplied buffer of type data_t */

/* also allocates memory for the copy of the string field
   of this data buffer, and the caller is responsible for
   freeing it */

/* gives a NULL pointer for the string field of *datap
   to signify that there are no more entries to examine;
   no need to call freekey() at that point */

/* if successful, returns 0 */
/* if unsuccessful, returns -1 and sets errno */
int getdata (int key, data_t* datap);

/* returns a non-negative integer key for traversing the data list;
   each key value produces an independent traversal starting
   from the beginning of the list; when the key is no longer
   needed, the caller must free the key resources by calling
   freekey() */
int accessdata (void);

/* frees key resources associated with key key */

/* do not call freekey once you have reached the end of the list */

/* if successful, returns 0 */
/* if unsuccessful, returns -1 and sets errno */
int freekey (int key);
