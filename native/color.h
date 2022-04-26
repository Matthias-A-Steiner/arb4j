#include <acb.h>

#include <jni.h>

void  color_function(double * R, double * G, double * B, const acb_t z, int mode);

void nthHardyZero( arb_t res, int n, slong prec );

void acb_calc_gl_node(arb_t x, arb_t w, slong i, slong k, slong prec);

void heap_up(acb_ptr as, acb_ptr bs, acb_ptr vs, mag_ptr ms, slong n);

void heap_down(acb_ptr as, acb_ptr bs, acb_ptr vs, mag_ptr ms, slong n);
