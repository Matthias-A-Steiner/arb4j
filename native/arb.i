%module arblib

%include typemaps.i


#define __signed__
#define slong signed long
#define ulong unsigned long
#define fmpr_rnd_t int
#define arf_rnd_t int

typedef mag_struct mag_t[1];
typedef mag_struct * mag_ptr;

%typemap(in) slong = long;

SWIG_JAVABODY_PROXY(public, public, SWIGTYPE)
SWIG_JAVABODY_TYPEWRAPPER(public, public, public, SWIGTYPE)

%rename (Integer) __mpz_struct;
%rename (FloatInterval) arf_interval_struct;
%rename (MultiPrecFloat) __mpfr_struct;
%rename (Complex) acb_struct;
%rename (Real) arb_struct;
%rename (Float) arf_struct;
%rename (Magnitude) mag_struct;
%rename (Mantissa) mantissa_struct;
%rename (MantissaPointer) mantissa_ptr_struct;
%rename (MantissaNoPointer) mantissa_noptr_struct;
%rename (DirichletCharacter) dirichlet_char_struct;
%rename (DirichletGroup) dirichlet_group_struct;
%rename (DirichletPrimeGroup) dirichlet_prime_group_struct;
%rename (ComplexPolynomial) acb_poly_struct;
%rename (ComplexMatrix) acb_mat_struct;
%rename (RealMatrix) arb_mat_struct;
%rename (FoundRoots) root_struct;

%include "Magnitude.i"

%include "Float.i"

%include "FloatInterval.i"

%include "Real.i"


%include "functions.i"

%rename (realObj) real;
%rename (imagObj) imag;

%include "Complex.i"

%rename (realObj) realObj;
%rename (imagObj) imagObj;


%include "ComplexPolynomial.i"

%include "ComplexMatrix.i"

%include "RealMatrix.i"


%include "TypesAndStructures.i"





   