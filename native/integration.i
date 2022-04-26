
void heap_up(acb_ptr as, acb_ptr bs, acb_ptr vs, mag_ptr ms, slong n)
{
    slong i, max, l, r;
    i = 0;
    for (;;)
    {
        max = i;
        l = 2 * i + 1;
        r = 2 * i + 2;
        if (l < n && mag_cmp(ms + l, ms + max) > 0)
            max = l;
        if (r < n && mag_cmp(ms + r, ms + max) > 0)
            max = r;
        if (max != i)
        {
            acb_swap(as + i, as + max);
            acb_swap(bs + i, bs + max);
            acb_swap(vs + i, vs + max);
            mag_swap(ms + i, ms + max);
            i = max;
        }
        else
        {
            break;
        }
    }
}

void heap_down(acb_ptr as, acb_ptr bs, acb_ptr vs, mag_ptr ms, slong n)
{
    slong j, k;

    k = n - 1;
    j = (k - 1) / 2;

    while (k > 0 && mag_cmp(ms + j, ms + k) < 0)
    {
        acb_swap(as + j, as + k);
        acb_swap(bs + j, bs + k);
        acb_swap(vs + j, vs + k);
        mag_swap(ms + j, ms + k);
        k = j;
        j = (j - 1) / 2;
    }
}
